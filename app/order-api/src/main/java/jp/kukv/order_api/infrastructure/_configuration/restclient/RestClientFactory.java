package jp.kukv.order_api.infrastructure._configuration.restclient;

import java.time.Duration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RestClientFactory {

  RestClient.Builder builder;

  public RestClient create(String baseUrl) {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(Duration.ofSeconds(10));

    return builder
        .clone()
        .requestFactory(factory)
        .baseUrl(baseUrl)
        .requestInterceptors(
            clientHttpRequestInterceptor ->
                clientHttpRequestInterceptor.add(new LoggingInterceptor()))
        .build();
  }

  RestClientFactory(RestClient.Builder builder) {
    this.builder = builder;
  }
}
