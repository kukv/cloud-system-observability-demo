package jp.kukv.user_api.domain.model.contact.address;

import com.fasterxml.jackson.annotation.JsonValue;

public class BuildingNumber {

  @JsonValue String value;

  BuildingNumber(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  BuildingNumber() {}
}
