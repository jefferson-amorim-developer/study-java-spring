package com.example.tddexampleproject.example01;

public class Money implements Expression {

  protected int amount;
  protected String currency;

  protected Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public static Money dollar(int amount) {
    return new Money(amount, "USD");
  }

  public static Money franc(int amount) {
    return new Money(amount, "CHF");
  }

  protected String currency() {
    return this.currency;
  }

  @Override
  public Expression times(int multiplier) {
    return new Money(this.amount * multiplier, this.currency);
  }

  @Override
  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  @Override
  public Money reduce(Bank bank, String toCurrency) {
    int rate = bank.rate(this.currency, toCurrency);
    return new Money(amount / rate, toCurrency);
  }



  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this == obj) {
      return true;
    }

    Money money = (Money) obj;
    return this.amount == money.amount && this.currency.equals(money.currency);
  }

  @Override
  public String toString() {
    return "Money [amount=" + amount + ", currency=" + currency + "]";
  }



}
