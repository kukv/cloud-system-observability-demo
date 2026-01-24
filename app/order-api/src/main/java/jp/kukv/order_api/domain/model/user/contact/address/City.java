package jp.kukv.order_api.domain.model.user.contact.address;

import com.fasterxml.jackson.annotation.JsonValue;

class City {

  @JsonValue String value;

  City(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  City() {}
}
