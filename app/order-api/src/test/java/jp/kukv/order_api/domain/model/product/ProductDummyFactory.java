package jp.kukv.order_api.domain.model.product;

import jp.kukv.order_api.domain.model.product.overview.Overview;

public class ProductDummyFactory {
  public static Product create(Overview overview, int price, int stock) {
    return new Product(overview, new Price(price), new Stock(stock));
  }
}
