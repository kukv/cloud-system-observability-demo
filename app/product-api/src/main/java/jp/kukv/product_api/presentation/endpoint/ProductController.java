package jp.kukv.product_api.presentation.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.kukv.product_api.application.service.ProductService;
import jp.kukv.product_api.domain.model.Id;
import jp.kukv.product_api.domain.model.Product;
import jp.kukv.product_api.presentation.openapi.response.BadRequest;
import jp.kukv.product_api.presentation.openapi.response.InternalServerError;
import jp.kukv.product_api.presentation.openapi.response.NotFound;
import jp.kukv.product_api.presentation.openapi.response.Success;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product")
@Tag(name = "product", description = "Product management API")
class ProductController {
  ProductService productService;

  @Operation(
      operationId = "商品取得",
      summary = "商品取得",
      description = "商品を取得する。",
      responses = {
        @ApiResponse(
            responseCode = Success.code,
            description = Success.description,
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(description = "商品", implementation = Product.class))),
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
  @GetMapping
  Product get(@RequestParam("id") Id id) {
    return productService.get(id);
  }

  ProductController(ProductService productService) {
    this.productService = productService;
  }
}
