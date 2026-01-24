package jp.kukv.user_api.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class Id {

  @JsonValue int value;

  public Id(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  Id() {}
}
