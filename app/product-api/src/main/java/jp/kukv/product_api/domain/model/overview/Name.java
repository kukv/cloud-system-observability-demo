package jp.kukv.product_api.domain.model.overview;

import com.fasterxml.jackson.annotation.JsonValue;

class Name {

  @JsonValue String value;

  public Name(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  Name() {}
}
