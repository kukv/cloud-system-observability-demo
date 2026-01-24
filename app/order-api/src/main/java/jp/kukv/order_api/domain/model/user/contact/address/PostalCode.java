package jp.kukv.order_api.domain.model.user.contact.address;

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
