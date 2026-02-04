package jp.kukv.api_caller.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class UserId {

  @JsonValue int value;

  UserId(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  static int GENERATE_MAX_VALUE = 10;

  public static UserId random() {
    int randomValue = (int) (Math.random() * GENERATE_MAX_VALUE);
    return new UserId(randomValue);
  }

  UserId() {}
}
