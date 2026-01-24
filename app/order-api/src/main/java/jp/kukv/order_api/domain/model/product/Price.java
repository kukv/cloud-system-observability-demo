package jp.kukv.order_api.domain.model.product;

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

  Price() {}
}
