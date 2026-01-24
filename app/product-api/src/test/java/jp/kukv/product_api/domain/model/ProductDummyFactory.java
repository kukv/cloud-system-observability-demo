package jp.kukv.product_api.domain.model;

public class ProductDummyFactory {
  public static Price createPrice(int price) {
    return new Price(price);
  }

  public static Stock createStock(int stock) {
    return new Stock(stock);
  }
}
