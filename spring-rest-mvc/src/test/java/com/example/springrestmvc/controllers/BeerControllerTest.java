package com.example.springrestmvc.controllers;

import static com.example.springrestmvc.controllers.BeerController.RESOURCE_PATH;
import static com.example.springrestmvc.controllers.BeerController.RESOURCE_PATH_ID;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.springrestmvc.model.BeerDTO;
import com.example.springrestmvc.services.BeerService;
import com.example.springrestmvc.services.BeerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

// @SpringBootTest
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  BeerService beerService;

  BeerServiceImpl beerServiceImpl;

  @Captor
  ArgumentCaptor<UUID> uuidArgumentCaptor;

  @Captor
  ArgumentCaptor<BeerDTO> beerArgumentCaptor;

  @BeforeEach
  void setUp() {
    beerServiceImpl = new BeerServiceImpl();
  }

  @Test
  void testGetBeerById() throws Exception {
    BeerDTO testBeer = beerServiceImpl.listBeers().get(0);

    given(beerService.getBeerById(testBeer.getId())).willReturn(Optional.of(testBeer));

    mockMvc.perform(get(RESOURCE_PATH_ID, testBeer.getId()).accept(APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(testBeer.getId().toString())))
        .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())))
        .andExpect(jsonPath("$.beerStyle", is(testBeer.getBeerStyle().toString())));
  }

  @Test
  void getBeerByIdNotFound() throws Exception {

    given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.empty());

    mockMvc.perform(get(RESOURCE_PATH_ID, UUID.randomUUID())).andExpect(status().isNotFound());
  }

  @Test
  void testListBeers() throws Exception {

    given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());

    mockMvc.perform(get(RESOURCE_PATH).accept(APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(3)));

  }

  @Test
  void testCreateNewBeer() throws Exception {

    BeerDTO beer = beerServiceImpl.listBeers().get(0);
    beer.setVersion(null);
    beer.setId(null);
    beer.setCreatedDate(null);
    beer.setUpdateDate(null);

    given(beerService.saveNewBeer(any(BeerDTO.class)))
        .willReturn(beerServiceImpl.listBeers().get(1));

    mockMvc
        .perform(post(RESOURCE_PATH).accept(APPLICATION_JSON).contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(beer)))
        .andExpect(status().isCreated()).andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(header().exists("Location"));
  }

  @Test
  void testUpdateBeer() throws Exception {

    BeerDTO beer = beerServiceImpl.listBeers().get(0);

    given(beerService.updateById(any(), any())).willReturn(Optional.of(beer));

    mockMvc
        .perform(put(RESOURCE_PATH_ID, beer.getId()).accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(beer)))
        .andExpect(status().isNoContent());

    verify(beerService).updateById(eq(beer.getId()), any(BeerDTO.class));
  }

  @Test
  void testDeleteBeer() throws Exception {

    BeerDTO beer = beerServiceImpl.listBeers().get(0);

    given(beerService.deleteById(any())).willReturn(true);

    mockMvc.perform(delete(RESOURCE_PATH_ID, beer.getId()).accept(APPLICATION_JSON))
        .andExpect(status().isNoContent());

    ArgumentCaptor<UUID> captorId = ArgumentCaptor.forClass(UUID.class);
    verify(beerService).deleteById(captorId.capture());

    assertEquals(beer.getId(), captorId.getValue());

  }

  @Test
  void testPatchBeer() throws Exception {
    BeerDTO beer = beerServiceImpl.listBeers().get(0);


    Map<String, Object> beerMap = new HashMap<>();
    beerMap.put("beerName", "New Name");

    mockMvc
        .perform(patch(RESOURCE_PATH_ID, beer.getId()).accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(beerMap)))
        .andExpect(status().isNoContent());

    verify(beerService).patchBeerById(uuidArgumentCaptor.capture(), beerArgumentCaptor.capture());

    assertEquals(beer.getId(), uuidArgumentCaptor.getValue());
    assertEquals(beerMap.get("beerName"), beerArgumentCaptor.getValue().getBeerName());
  }
}
