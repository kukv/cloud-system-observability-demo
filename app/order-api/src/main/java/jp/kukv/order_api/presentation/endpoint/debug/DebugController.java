package jp.kukv.order_api.presentation.endpoint.debug;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import jp.kukv.order_api.infrastructure._configuration.external.Endpoint;
import jp.kukv.order_api.infrastructure._configuration.restclient.RestClientFactory;
import jp.kukv.order_api.presentation.openapi.response.InternalServerError;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/v1/debug")
@Tag(name = "user", description = "デバッグ用API")
class DebugController {

  RestClient restClient;

  @Operation(
      operationId = "InternalServerErrorを意図的に起こすエンドポイント",
      summary = "InternalServerErrorを意図的に起こすエンドポイント",
      description = "InternalServerErrorを意図的に起こします。",
      responses = {
        @ApiResponse(
            responseCode = InternalServerError.code,
            description = InternalServerError.description,
            content = @Content),
      })
  @GetMapping("/internal-server-error")
  void onInternalServerError() {
    throw new RuntimeException("This is an intentionally thrown error for debugging purposes.");
  }

  @Operation(
      operationId = "OOMを意図的に引き起こすエンドポイント",
      summary = "OOMを意図的に引き起こすエンドポイント",
      description = "OOMを意図的に起こします。",
      responses = {
        @ApiResponse(
            responseCode = InternalServerError.code,
            description = InternalServerError.description,
            content = @Content),
      })
  @GetMapping("/oom")
  void onOutOfMemory(
      @RequestParam(name = "final-confirmation", defaultValue = "No") String answer) {
    if (!answer.equals("Yes")) {
      return;
    }

    List<byte[]> list = new ArrayList<>();

    try {
      while (true) {
        list.add(new byte[1024 * 1024]);
      }
    } catch (OutOfMemoryError e) {
      throw new RuntimeException(e);
    }
  }

  @Operation(
      operationId = "意図的に連鎖的なInternalServerErrorを引き起こすエンドポイント",
      summary = "意図的に連鎖的なInternalServerErrorを引き起こすエンドポイント",
      description = "連鎖的なInternalServerErrorを意図的に起こします。",
      responses = {
        @ApiResponse(
            responseCode = InternalServerError.code,
            description = InternalServerError.description,
            content = @Content),
      })
  @GetMapping("/chain-reaction-internal-server-rrror")
  void onChainReactionInternalServerError() {
    try {
      restClient
          .get()
          .uri(uriBuilder -> uriBuilder.path("/v1/debug/internal-server-error").build())
          .retrieve()
          .toBodilessEntity();
    } catch (Exception e) {
      throw new RuntimeException("Failed to retrieve user-api", e);
    }
  }

  DebugController(RestClientFactory restClientFactory, @Qualifier("userApi") Endpoint userApi) {
    this.restClient = restClientFactory.create(userApi.baseUrl());
  }
}
