package com.example.tddexampleproject.example01;

import java.util.HashMap;

public class Bank {

  private HashMap<Pair, Integer> rateMap = new HashMap<>();

  public Money reduce(Expression source, String toCurrency) {
    return source.reduce(this, toCurrency);
  }

  public Integer rate(String from, String to) {
    Integer rate = this.rateMap.get(new Pair(from, to));
    return rate == null ? 1 : rate;
  }

  public void addRate(String from, String to, int rate) {
    this.rateMap.put(new Pair(from, to), rate);
  }


}
