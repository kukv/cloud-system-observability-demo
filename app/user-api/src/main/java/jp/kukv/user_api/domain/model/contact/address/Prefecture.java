package jp.kukv.user_api.domain.model.contact.address;

import com.fasterxml.jackson.annotation.JsonValue;

class Prefecture {

  @JsonValue String value;

  Prefecture(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  Prefecture() {}
}
