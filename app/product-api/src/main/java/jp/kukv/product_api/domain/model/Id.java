package jp.kukv.product_api.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class Id {

  @JsonValue int value;

  public Id(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public Id() {}
}
