package com.example.springdiexampleproject.controllers.i18n;

import com.example.springdiexampleproject.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class Myi18NController {

  private GreetingService greetingService;

  public Myi18NController(@Qualifier("i18NService") GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  public String sayHello() {
    return this.greetingService.sayGreeting();
  }
}
