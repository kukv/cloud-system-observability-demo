package jp.kukv.api_caller.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class Quantity {

  @JsonValue int value;

  Quantity(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }

  static int GENERATE_MAX_VALUE = 100;

  public static Quantity random() {
    int randomValue = (int) (Math.random() * GENERATE_MAX_VALUE);
    return new Quantity(randomValue);
  }

  Quantity() {}
}
