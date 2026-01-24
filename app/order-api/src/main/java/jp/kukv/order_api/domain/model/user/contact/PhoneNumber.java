package jp.kukv.order_api.domain.model.user.contact;

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
