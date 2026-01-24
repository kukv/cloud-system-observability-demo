package jp.kukv.order_api.domain.model.user.profile;

import com.fasterxml.jackson.annotation.JsonValue;

class FirstName {

  @JsonValue String value;

  FirstName(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  FirstName() {}
}
