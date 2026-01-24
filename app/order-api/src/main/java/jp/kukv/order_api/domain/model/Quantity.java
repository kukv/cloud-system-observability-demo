package jp.kukv.order_api.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.Min;

public class Quantity {

  @Min(value = 1, message = "数量は1個以上")
  @JsonValue
  int value;

  Quantity(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }

  Quantity() {}
}
