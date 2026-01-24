package jp.kukv.order_api.domain.model.product.overview;

import com.fasterxml.jackson.annotation.JsonValue;

class Name {

  @JsonValue String value;

  Name(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  Name() {}
}
