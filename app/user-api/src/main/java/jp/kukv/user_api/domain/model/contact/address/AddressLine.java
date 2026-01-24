package jp.kukv.user_api.domain.model.contact.address;

import com.fasterxml.jackson.annotation.JsonValue;

class AddressLine {

  @JsonValue String value;

  AddressLine(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  AddressLine() {}
}
