package jp.kukv.order_api.presentation.endpoint;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Hidden
class IndexController {

  @GetMapping("favicon.ico")
  void returnNoFavicon() {}

  IndexController() {}
}
