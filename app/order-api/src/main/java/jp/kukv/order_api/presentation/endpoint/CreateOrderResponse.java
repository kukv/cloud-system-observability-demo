package jp.kukv.order_api.presentation.endpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.kukv.order_api.domain.model.Id;

class CreateOrderResponse {

  @JsonProperty Id id;

  CreateOrderResponse(Id id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "CreateOrderResponse{" + "id=" + id + '}';
  }

  CreateOrderResponse() {}
}
