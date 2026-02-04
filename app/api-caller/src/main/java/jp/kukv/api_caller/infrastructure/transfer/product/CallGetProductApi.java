package jp.kukv.api_caller.infrastructure.transfer.product;

import jp.kukv.api_caller.application.repository.ApiCallRepository;
import jp.kukv.api_caller.domain.model.ProductId;
import jp.kukv.api_caller.infrastructure._configuration.external.Endpoint;
import jp.kukv.api_caller.infrastructure._configuration.restclient.RestClientFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
class CallGetProductApi implements ApiCallRepository {

  RestClient restClient;

  @Override
  public void call() {
    try {
      ProductId productId = ProductId.random();
      restClient
          .get()
          .uri(
              uriBuilder ->
                  uriBuilder.path("/v1/product").queryParam("id", productId.toString()).build())
          .retrieve()
          .toBodilessEntity();
    } catch (Exception _) {
      // 何もしない
    }
  }

  CallGetProductApi(RestClientFactory factory, @Qualifier("productApi") Endpoint endpoint) {
    this.restClient = factory.create(endpoint.baseUrl());
  }
}
