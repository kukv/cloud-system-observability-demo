package jp.kukv.product_api.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class Price {

  @JsonValue int value;

  Price(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }

  public Price() {}
}
