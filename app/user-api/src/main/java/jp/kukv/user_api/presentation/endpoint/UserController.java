package jp.kukv.user_api.presentation.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.kukv.user_api.application.service.UserService;
import jp.kukv.user_api.domain.model.Id;
import jp.kukv.user_api.domain.model.User;
import jp.kukv.user_api.presentation.openapi.response.BadRequest;
import jp.kukv.user_api.presentation.openapi.response.InternalServerError;
import jp.kukv.user_api.presentation.openapi.response.NotFound;
import jp.kukv.user_api.presentation.openapi.response.Success;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@Tag(name = "user", description = "ユーザー管理API")
class UserController {
  UserService userService;

  @Operation(
      operationId = "ユーザーの取得",
      summary = "ユーザーの取得",
      description = "ユーザー情報を取得する",
      responses = {
        @ApiResponse(
            responseCode = Success.code,
            description = Success.description,
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(description = "ユーザー", implementation = User.class))),
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
  User get(@RequestParam("id") Id id) {
    return userService.get(id);
  }

  UserController(UserService userService) {
    this.userService = userService;
  }
}
