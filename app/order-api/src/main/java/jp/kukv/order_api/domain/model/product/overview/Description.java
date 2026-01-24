package jp.kukv.order_api.domain.model.product.overview;

import com.fasterxml.jackson.annotation.JsonValue;

class Description {

  @JsonValue String value;

  Description(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  Description() {}
}
