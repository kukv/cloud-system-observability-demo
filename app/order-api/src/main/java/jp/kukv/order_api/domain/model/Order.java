package jp.kukv.order_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.kukv.order_api.domain.model.user.UserId;

public class Order {

  @JsonProperty Id id;

  @JsonProperty UserId userId;

  Order(Id id, UserId userId) {
    this.id = id;
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Order{" + "id=" + id + ", userId=" + userId + '}';
  }

  Order() {}
}
