package jp.kukv.api_caller.infrastructure.transfer.order;

import jp.kukv.api_caller.application.repository.ApiCallRepository;
import jp.kukv.api_caller.domain.model.ProductId;
import jp.kukv.api_caller.domain.model.Quantity;
import jp.kukv.api_caller.domain.model.UserId;
import jp.kukv.api_caller.infrastructure._configuration.external.Endpoint;
import jp.kukv.api_caller.infrastructure._configuration.restclient.RestClientFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
class CallPostOrderApi implements ApiCallRepository {

  RestClient restClient;

  @Override
  public void call() {
    try {
      CreateOrderRequest request =
          CreateOrderRequest.of(UserId.random(), ProductId.random(), Quantity.random());

      restClient.post().uri("/v1/order/create").body(request).retrieve().toBodilessEntity();
    } catch (Exception _) {
      // 何もしない
    }
  }

  CallPostOrderApi(RestClientFactory factory, @Qualifier("orderApi") Endpoint endpoint) {
    this.restClient = factory.create(endpoint.baseUrl());
  }
}
