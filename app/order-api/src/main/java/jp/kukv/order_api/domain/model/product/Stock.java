package jp.kukv.order_api.domain.model.product;

import com.fasterxml.jackson.annotation.JsonValue;

public class Stock {

  @JsonValue int value;

  Stock(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }

  Stock() {}
}
