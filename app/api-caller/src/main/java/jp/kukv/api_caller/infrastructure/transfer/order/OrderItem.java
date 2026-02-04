package jp.kukv.api_caller.infrastructure.transfer.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.kukv.api_caller.domain.model.ProductId;
import jp.kukv.api_caller.domain.model.Quantity;

class OrderItem {

  @JsonProperty ProductId productId;

  @JsonProperty Quantity quantity;

  OrderItem(ProductId productId, Quantity quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "OrderItem{" + "productId=" + productId + ", quantity=" + quantity + '}';
  }

  OrderItem() {}
}
