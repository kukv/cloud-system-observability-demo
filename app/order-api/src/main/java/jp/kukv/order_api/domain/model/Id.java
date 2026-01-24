package jp.kukv.order_api.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class Id {

  @JsonValue int value;

  public Id(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  @Override
  public String toString() {
    return "OrderId{value=" + value + '}';
  }

  Id() {}
}
