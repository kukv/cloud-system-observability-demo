package jp.kukv.order_api.domain.model.product;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.Min;

public class ProductId {

  @Min(value = 1, message = "商品IDは1以上")
  @JsonValue
  int value;

  public ProductId(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  ProductId() {}
}
