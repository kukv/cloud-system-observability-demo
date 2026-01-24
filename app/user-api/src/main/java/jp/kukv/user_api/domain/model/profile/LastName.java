package jp.kukv.user_api.domain.model.profile;

import com.fasterxml.jackson.annotation.JsonValue;

class LastName {

  @JsonValue String value;

  LastName(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  LastName() {}
}
