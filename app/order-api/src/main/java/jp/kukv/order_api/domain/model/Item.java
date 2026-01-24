package jp.kukv.order_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jp.kukv.order_api.domain.model.product.Price;
import jp.kukv.order_api.domain.model.product.ProductId;

public class Item {

  @NotNull @Valid @JsonProperty ProductId productId;

  @NotNull @Valid @JsonProperty Price unitPrice;

  @NotNull @Valid @JsonProperty Quantity quantity;

  public Item(ProductId productId, Price unitPrice, Quantity quantity) {
    this.productId = productId;
    this.unitPrice = unitPrice;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Item{"
        + "productId="
        + productId
        + ", unitPrice="
        + unitPrice
        + ", quantity="
        + quantity
        + '}';
  }

  public Item() {}
}
