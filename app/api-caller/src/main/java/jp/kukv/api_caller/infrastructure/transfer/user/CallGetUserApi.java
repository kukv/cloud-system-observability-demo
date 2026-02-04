package jp.kukv.api_caller.infrastructure.transfer.user;

import jp.kukv.api_caller.application.repository.ApiCallRepository;
import jp.kukv.api_caller.domain.model.UserId;
import jp.kukv.api_caller.infrastructure._configuration.external.Endpoint;
import jp.kukv.api_caller.infrastructure._configuration.restclient.RestClientFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
class CallGetUserApi implements ApiCallRepository {

  RestClient restClient;

  @Override
  public void call() {
    try {
      UserId userId = UserId.random();
      restClient
          .get()
          .uri(
              uriBuilder -> uriBuilder.path("/v1/user").queryParam("id", userId.toString()).build())
          .retrieve()
          .toBodilessEntity();
    } catch (Exception _) {
      // 何もしない
    }
  }

  CallGetUserApi(RestClientFactory factory, @Qualifier("userApi") Endpoint endpoint) {
    this.restClient = factory.create(endpoint.baseUrl());
  }
}
