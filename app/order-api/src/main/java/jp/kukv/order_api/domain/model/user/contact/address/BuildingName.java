package jp.kukv.order_api.domain.model.user.contact.address;

import com.fasterxml.jackson.annotation.JsonValue;

class BuildingName {

  @JsonValue String value;

  BuildingName(String value) {
    this.value = value;
  }

  boolean isEmpty() {
    return value.isEmpty();
  }

  @Override
  public String toString() {
    return value;
  }

  BuildingName() {}
}
