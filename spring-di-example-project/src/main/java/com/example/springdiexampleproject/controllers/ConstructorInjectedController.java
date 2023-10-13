package com.example.springdiexampleproject.controllers;

import com.example.springdiexampleproject.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ConstructorInjectedController {

  private final GreetingService greetingService;

  public ConstructorInjectedController(
      @Qualifier("greetingProperty") GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  public String sayHello() {
    return this.greetingService.sayGreeting();

  }


}
