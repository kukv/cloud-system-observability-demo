package jp.kukv.product_api.domain.model.overview;

import com.fasterxml.jackson.annotation.JsonValue;

class Description {

  @JsonValue String value;

  public Description(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  Description() {}
}
