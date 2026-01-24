package jp.kukv.user_api.domain.model.contact.address;

import com.fasterxml.jackson.annotation.JsonValue;

class PostalCode {

  @JsonValue String value;

  public PostalCode(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  PostalCode() {}
}
