package com.example.tddexampleproject.example01;

import java.util.Objects;

public class Pair {
  private final String from;
  private final String to;

  public Pair(String from, String to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }

    Pair pair = (Pair) obj;
    return Objects.equals(from, pair.from) && Objects.equals(to, pair.to);
  }



}
