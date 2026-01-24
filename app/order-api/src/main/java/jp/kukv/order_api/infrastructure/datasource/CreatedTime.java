package jp.kukv.order_api.infrastructure.datasource;

import java.time.LocalDateTime;

public class CreatedTime {

  LocalDateTime value;

  CreatedTime(LocalDateTime value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  public static CreatedTime now() {
    return new CreatedTime(LocalDateTime.now());
  }

  CreatedTime() {}
}
