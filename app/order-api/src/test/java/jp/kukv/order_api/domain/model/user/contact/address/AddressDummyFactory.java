package jp.kukv.order_api.domain.model.user.contact.address;

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
