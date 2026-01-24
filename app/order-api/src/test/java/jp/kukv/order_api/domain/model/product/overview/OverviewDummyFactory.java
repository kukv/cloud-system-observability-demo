package jp.kukv.order_api.domain.model.product.overview;

public class OverviewDummyFactory {
  public static Overview create(String name, String description) {
    return new Overview(new Name(name), new Description(description));
  }
}
