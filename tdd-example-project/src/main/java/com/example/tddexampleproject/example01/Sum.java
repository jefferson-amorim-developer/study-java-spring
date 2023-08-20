package com.example.tddexampleproject.example01;

public class Sum implements Expression {

  Expression augmend;
  Expression addmend;

  public Sum(Expression augmend, Expression addmend) {
    super();
    this.augmend = augmend;
    this.addmend = addmend;
  }

  @Override
  public Money reduce(Bank bank, String toCurrency) {
    int amount =
        this.augmend.reduce(bank, toCurrency).amount + this.addmend.reduce(bank, toCurrency).amount;
    return new Money(amount, toCurrency);
  }

  @Override
  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  @Override
  public Expression times(int multiplier) {
    return new Sum(this.augmend.times(multiplier), this.addmend.times(multiplier));
  }



}
