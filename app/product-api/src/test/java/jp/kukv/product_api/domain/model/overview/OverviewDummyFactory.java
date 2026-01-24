package jp.kukv.product_api.domain.model.overview;

public class OverviewDummyFactory {
  public static Overview create(String name, String description) {
    return new Overview(new Name(name), new Description(description));
  }
}
