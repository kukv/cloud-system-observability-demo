package jp.kukv.user_api.domain.model.contact.address;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

  @JsonProperty PostalCode postalCode;

  @JsonProperty Prefecture prefecture;

  @JsonProperty City city;

  @JsonProperty AddressLine addressLine;

  @JsonProperty BuildingName buildingName = new BuildingName("");

  @JsonProperty BuildingNumber buildingNumber = new BuildingNumber("");

  Address(
      PostalCode postalCode,
      Prefecture prefecture,
      City city,
      AddressLine addressLine,
      BuildingName buildingName,
      BuildingNumber buildingNumber) {
    this.postalCode = postalCode;
    this.prefecture = prefecture;
    this.city = city;
    this.addressLine = addressLine;
    this.buildingName = buildingName;
    this.buildingNumber = buildingNumber;
  }

  @Override
  public String toString() {
    return "Address{"
        + "postalCode="
        + postalCode
        + ", prefecture="
        + prefecture
        + ", city="
        + city
        + ", addressLine="
        + addressLine
        + ", buildingName="
        + buildingName
        + ", buildingNumber="
        + buildingNumber
        + '}';
  }

  Address() {}
}
