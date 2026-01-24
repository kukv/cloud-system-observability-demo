package jp.kukv.order_api.infrastructure.receive.user;

import jp.kukv.order_api.application.repository.user.UserRepository;
import jp.kukv.order_api.domain.model.user.User;
import jp.kukv.order_api.domain.model.user.UserId;
import jp.kukv.order_api.domain.policy.exception.ResourceNotFoundException;
import jp.kukv.order_api.infrastructure._configuration.external.Endpoint;
import jp.kukv.order_api.infrastructure._configuration.restclient.RestClientFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Repository
class UserReceive implements UserRepository {

  RestClient restClient;

  @Override
  public User get(UserId userId) {
    try {
      return restClient
          .get()
          .uri(
              uriBuilder -> uriBuilder.path("/v1/user").queryParam("id", userId.toString()).build())
          .retrieve()
          .body(User.class);
    } catch (HttpClientErrorException.NotFound exception) {
      throw new ResourceNotFoundException(String.format("User not found. id: %s", userId));
    } catch (Exception e) {
      throw new RuntimeException("Failed to retrieve user", e);
    }
  }

  UserReceive(RestClientFactory restClientFactory, @Qualifier("userApi") Endpoint userApi) {
    this.restClient = restClientFactory.create(userApi.baseUrl());
  }
}
