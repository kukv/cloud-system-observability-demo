package jp.kukv.api_caller.infrastructure.transfer.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.kukv.api_caller.domain.model.ProductId;
import jp.kukv.api_caller.domain.model.Quantity;
import jp.kukv.api_caller.domain.model.UserId;

class CreateOrderRequest {

  @JsonProperty UserId userId;

  @JsonProperty OrderItem orderItem;

  CreateOrderRequest(UserId userId, OrderItem orderItem) {
    this.userId = userId;
    this.orderItem = orderItem;
  }

  @Override
  public String toString() {
    return "CreateOrderRequest{" + "userId=" + userId + ", orderItem=" + orderItem + '}';
  }

  static CreateOrderRequest of(UserId userId, ProductId productId, Quantity quantity) {
    return new CreateOrderRequest(userId, new OrderItem(productId, quantity));
  }

  CreateOrderRequest() {}
}
