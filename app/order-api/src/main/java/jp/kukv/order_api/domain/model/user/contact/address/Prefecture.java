package jp.kukv.order_api.domain.model.user.contact.address;

import com.fasterxml.jackson.annotation.JsonValue;

class Prefecture {

  @JsonValue String value;

  public Prefecture(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  Prefecture() {}
}
