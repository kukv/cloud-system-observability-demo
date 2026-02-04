package jp.kukv.api_caller.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class ProductId {

  @JsonValue int value;

  public ProductId(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  static int GENERATE_MAX_VALUE = 15;

  public static ProductId random() {
    int randomValue = (int) (Math.random() * GENERATE_MAX_VALUE);
    return new ProductId(randomValue);
  }

  ProductId() {}
}
