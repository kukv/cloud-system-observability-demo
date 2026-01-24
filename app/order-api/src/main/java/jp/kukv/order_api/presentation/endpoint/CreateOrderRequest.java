package jp.kukv.order_api.presentation.endpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jp.kukv.order_api.domain.model.OrderItem;
import jp.kukv.order_api.domain.model.user.UserId;

class CreateOrderRequest {

  @NotNull(message = "ユーザーIDは必須")
  @Valid
  @JsonProperty
  UserId userId;

  @NotNull(message = "注文商品は必須")
  @Valid
  @JsonProperty
  OrderItem orderItem;

  CreateOrderRequest(UserId userId, OrderItem orderItem) {
    this.userId = userId;
    this.orderItem = orderItem;
  }

  @Override
  public String toString() {
    return "CreateOrderRequest{" + "userId=" + userId + ", orderItem=" + orderItem + '}';
  }

  CreateOrderRequest() {}
}
