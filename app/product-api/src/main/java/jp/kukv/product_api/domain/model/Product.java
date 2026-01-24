package jp.kukv.product_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.kukv.product_api.domain.model.overview.Overview;

public class Product {

  @JsonProperty Overview overview;

  @JsonProperty Price price;

  @JsonProperty Stock stock;

  Product(Overview overview, Price price, Stock stock) {
    this.overview = overview;
    this.price = price;
    this.stock = stock;
  }

  @Override
  public String toString() {
    return "Product{" + "overview=" + overview + ", price=" + price + ", stock=" + stock + '}';
  }

  Product() {}
}
