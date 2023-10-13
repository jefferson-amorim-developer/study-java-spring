package com.example.springdiexampleproject.controllers;

import com.example.springdiexampleproject.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class SetterInjectedController {

  private GreetingService greetingService;

  @Autowired
  @Qualifier("greetingServiceImpl")
  public void setGreetingService(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  public String sayHello() {
    return this.greetingService.sayGreeting();
  }



}
