package jp.kukv.order_api.domain.model.user.contact;

import com.fasterxml.jackson.annotation.JsonValue;

class MailAddress {

  @JsonValue String value;

  MailAddress(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  public MailAddress() {}
}
