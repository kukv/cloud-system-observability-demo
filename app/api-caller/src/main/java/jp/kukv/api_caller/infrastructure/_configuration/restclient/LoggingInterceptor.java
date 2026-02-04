package jp.kukv.api_caller.infrastructure._configuration.restclient;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

class LoggingInterceptor implements ClientHttpRequestInterceptor {

  Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    long start = System.currentTimeMillis();
    ClientHttpResponse response = execution.execute(request, body);
    long end = System.currentTimeMillis();
    log.info(
        "requested in {}ms. uri={}, status={}",
        end - start,
        request.getURI(),
        response.getStatusCode().value());
    return response;
  }
}
