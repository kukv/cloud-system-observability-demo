package jp.kukv.order_api.infrastructure.receive.product;

import jp.kukv.order_api.application.repository.product.ProductRepository;
import jp.kukv.order_api.domain.model.product.Product;
import jp.kukv.order_api.domain.model.product.ProductId;
import jp.kukv.order_api.domain.policy.exception.ResourceNotFoundException;
import jp.kukv.order_api.infrastructure._configuration.external.Endpoint;
import jp.kukv.order_api.infrastructure._configuration.restclient.RestClientFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Repository
class ProductReceive implements ProductRepository {

  RestClient restClient;

  @Override
  public Product get(ProductId productId) {
    try {
      return restClient
          .get()
          .uri(
              uriBuilder ->
                  uriBuilder.path("/v1/product").queryParam("id", productId.toString()).build())
          .retrieve()
          .body(Product.class);
    } catch (HttpClientErrorException.NotFound exception) {
      throw new ResourceNotFoundException(String.format("Product not found. id: %s", productId));
    } catch (Exception e) {
      throw new RuntimeException("Failed to retrieve user", e);
    }
  }

  ProductReceive(
      RestClientFactory restClientFactory, @Qualifier("productApi") Endpoint productApi) {
    this.restClient = restClientFactory.create(productApi.baseUrl());
  }
}
