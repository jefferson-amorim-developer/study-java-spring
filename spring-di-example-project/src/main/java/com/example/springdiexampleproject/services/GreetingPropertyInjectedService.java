package com.example.springdiexampleproject.services;

import org.springframework.stereotype.Service;

@Service("greetingProperty")
public class GreetingPropertyInjectedService implements GreetingService {

  @Override
  public String sayGreeting() {
    return "Hello Everyone from Property Injected!!!";
  }

}
