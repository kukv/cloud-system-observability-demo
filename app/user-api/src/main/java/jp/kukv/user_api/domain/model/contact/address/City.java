package jp.kukv.user_api.domain.model.contact.address;

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
