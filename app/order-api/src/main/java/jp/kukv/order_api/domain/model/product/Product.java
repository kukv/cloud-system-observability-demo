package jp.kukv.order_api.domain.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.kukv.order_api.domain.model.product.overview.Overview;

public class Product {

  @JsonProperty Overview overview;

  @JsonProperty Price price;

  @JsonProperty Stock stock;

  Product(Overview overview, Price price, Stock stock) {
    this.overview = overview;
    this.price = price;
    this.stock = stock;
  }

  public Price price() {
    return price;
  }

  @Override
  public String toString() {
    return "Product{" + "overview=" + overview + ", price=" + price + ", stock=" + stock + '}';
  }

  Product() {}
}
