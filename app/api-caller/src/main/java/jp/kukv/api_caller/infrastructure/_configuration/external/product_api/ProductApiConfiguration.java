package jp.kukv.api_caller.infrastructure._configuration.external.product_api;

import jp.kukv.api_caller.infrastructure._configuration.external.Endpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "external.endpoint.product-api")
class ProductApiConfiguration {

  String domain;
  String port;

  @Bean
  Endpoint productApi() {
    return new Endpoint(domain, port);
  }

  // for property binding
  void setDomain(String domain) {
    this.domain = domain;
  }

  // for property binding
  void setPort(String port) {
    this.port = port;
  }

  ProductApiConfiguration() {}
}
