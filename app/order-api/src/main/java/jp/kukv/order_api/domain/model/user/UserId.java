package jp.kukv.order_api.domain.model.user;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.Min;

public class UserId {

  @Min(value = 1, message = "ユーザーIDは1以上")
  @JsonValue
  int value;

  public UserId(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  UserId() {}
}
