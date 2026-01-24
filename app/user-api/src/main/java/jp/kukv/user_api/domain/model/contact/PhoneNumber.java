package jp.kukv.user_api.domain.model.contact;

import com.fasterxml.jackson.annotation.JsonValue;

class PhoneNumber {

  @JsonValue String value;

  PhoneNumber(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  PhoneNumber() {}
}
