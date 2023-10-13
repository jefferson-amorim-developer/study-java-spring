package com.example.springdiexampleproject.controllers;

import com.example.springdiexampleproject.services.GreetingService;
import com.example.springdiexampleproject.services.GreetingServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

  private final GreetingService greetingService;


  public MyController() {
    this.greetingService = new GreetingServiceImpl();
  }

  public String sayHello() {
    System.out.println("I'm in the controller");

    return this.greetingService.sayGreeting();

  }

  public void beforeInit() {
    System.out.println("## - Before Init - Called by Bean Post Processor");
  }

  public void afterInit() {
    System.out.println("## - After Init - Called by Bean Post Processor");
  }

}
