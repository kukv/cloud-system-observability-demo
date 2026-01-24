package jp.kukv.user_api.domain.model.contact.address;

import com.fasterxml.jackson.annotation.JsonValue;

class BuildingName {

  @JsonValue String value;

  BuildingName(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  BuildingName() {}
}
