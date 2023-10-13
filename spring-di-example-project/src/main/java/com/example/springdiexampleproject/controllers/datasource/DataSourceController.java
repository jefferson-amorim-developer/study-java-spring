package com.example.springdiexampleproject.controllers.datasource;

import com.example.springdiexampleproject.services.datasource.DataSourceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class DataSourceController {

  private final DataSourceService dataSourceService;

  public DataSourceController(@Qualifier("dataSourceService") DataSourceService dataSourceService) {
    this.dataSourceService = dataSourceService;
  }

  public String config() {
    return this.dataSourceService.value();
  }


}
