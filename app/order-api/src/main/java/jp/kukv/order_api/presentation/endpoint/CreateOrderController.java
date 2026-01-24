package jp.kukv.order_api.presentation.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.kukv.order_api.application.scenario.CreateOrderScenario;
import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.presentation.openapi.response.BadRequest;
import jp.kukv.order_api.presentation.openapi.response.Created;
import jp.kukv.order_api.presentation.openapi.response.InternalServerError;
import jp.kukv.order_api.presentation.openapi.response.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
@Tag(name = "order", description = "オーダーAPI")
class CreateOrderController {

  CreateOrderScenario createOrderScenario;

  @Operation(
      operationId = "オーダー作成",
      summary = "オーダー作成",
      description = "新規オーダーを作成します。",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "オーダー作成依頼",
              required = true,
              content =
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = CreateOrderRequest.class))),
      responses = {
        @ApiResponse(
            responseCode = Created.code,
            description = Created.description,
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CreateOrderResponse.class))),
        @ApiResponse(
            responseCode = BadRequest.code,
            description = BadRequest.description,
            content = @Content),
        @ApiResponse(
            responseCode = NotFound.code,
            description = NotFound.description,
            content = @Content),
        @ApiResponse(
            responseCode = InternalServerError.code,
            description = InternalServerError.description,
            content = @Content),
      })
  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  Id create(@Validated @RequestBody CreateOrderRequest request, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new IllegalArgumentException(bindingResult.toString());
    }

    return createOrderScenario.create(request.userId, request.orderItem);
  }

  CreateOrderController(CreateOrderScenario createOrderScenario) {
    this.createOrderScenario = createOrderScenario;
  }
}
