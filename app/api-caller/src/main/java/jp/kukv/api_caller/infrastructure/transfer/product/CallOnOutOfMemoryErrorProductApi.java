package jp.kukv.api_caller.infrastructure.transfer.product;

import jp.kukv.api_caller.application.repository.ApiCallRepository;
import jp.kukv.api_caller.infrastructure._configuration.external.Endpoint;
import jp.kukv.api_caller.infrastructure._configuration.restclient.RestClientFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
class CallOnOutOfMemoryErrorProductApi implements ApiCallRepository {

  RestClient restClient;

  @Override
  public void call() {
    try {
      restClient
          .get()
          .uri(
              uriBuilder ->
                  uriBuilder.path("/v1/debug/oom").queryParam("final-confirmation", "Yes").build())
          .retrieve()
          .toBodilessEntity();
    } catch (Exception _) {
      // 何もしない
    }
  }

  CallOnOutOfMemoryErrorProductApi(
      RestClientFactory factory, @Qualifier("productApi") Endpoint endpoint) {
    this.restClient = factory.create(endpoint.baseUrl());
  }
}
