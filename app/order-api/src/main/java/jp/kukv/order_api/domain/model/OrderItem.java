package jp.kukv.order_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jp.kukv.order_api.domain.model.product.ProductId;

public class OrderItem {

  @NotNull(message = "商品IDは必須")
  @Valid
  @JsonProperty
  ProductId productId;

  @NotNull(message = "数量は必須")
  @Valid
  @JsonProperty
  Quantity quantity;

  OrderItem(ProductId productId, Quantity quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public ProductId productId() {
    return productId;
  }

  public Quantity quantity() {
    return quantity;
  }

  @Override
  public String toString() {
    return "OrderItem{" + "productId=" + productId + ", quantity=" + quantity + '}';
  }

  OrderItem() {}
}
