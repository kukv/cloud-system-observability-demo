package jp.kukv.user_api.domain.model.contact.address;

public class AddressDummyFactory {
  public static Address create(
      String postalCode,
      String prefecture,
      String city,
      String addressLine,
      String buildingName,
      String buildingNumber) {
    return new Address(
        new PostalCode(postalCode),
        new Prefecture(prefecture),
        new City(city),
        new AddressLine(addressLine),
        new BuildingName(buildingName),
        new BuildingNumber(buildingNumber));
  }
}
