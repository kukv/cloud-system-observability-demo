package jp.kukv.order_api.infrastructure._configuration.external;

public class Endpoint {

  String domain;
  String port;

  public Endpoint(String domain, String port) {
    this.domain = domain;
    this.port = port;
  }

  public String baseUrl() {
    return String.format("http://%s:%s", domain, port);
  }

  @Override
  public String toString() {
    return "Domain{" + "domain='" + domain + '\'' + ", port='" + port + '\'' + '}';
  }

  Endpoint() {}
}
