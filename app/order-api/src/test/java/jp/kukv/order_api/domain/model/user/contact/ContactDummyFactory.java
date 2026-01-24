package jp.kukv.order_api.domain.model.user.contact;

import jp.kukv.order_api.domain.model.user.contact.address.Address;

public class ContactDummyFactory {
  public static Contact create(String mailAddress, String phoneNumber, Address address) {
    return new Contact(new MailAddress(mailAddress), new PhoneNumber(phoneNumber), address);
  }
}
