package jp.kukv.order_api.domain.model.user.contact.address;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

  @JsonProperty("postal_code")
  PostalCode postalCode;

  @JsonProperty Prefecture prefecture;

  @JsonProperty City city;

  @JsonProperty("address_line")
  AddressLine addressLine;

  @JsonProperty("building_name")
  BuildingName buildingName = new BuildingName("");

  @JsonProperty("building_number")
  BuildingNumber buildingNumber = new BuildingNumber("");

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

  // for mybatis
  public String getFullAddress() {
    StringBuilder builder = new StringBuilder();

    builder.append(postalCode).append(" ");
    builder.append(prefecture).append(" ");
    builder.append(city).append(" ");
    builder.append(addressLine);

    if (buildingName.isEmpty()) {
      builder.append(" ");
      builder.append(buildingName);
    }

    if (buildingNumber.isEmpty()) {
      builder.append(" ");
      builder.append(buildingNumber);
    }

    return builder.toString();
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
