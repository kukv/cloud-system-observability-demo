package jp.kukv.user_api.domain.model.profile;

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
