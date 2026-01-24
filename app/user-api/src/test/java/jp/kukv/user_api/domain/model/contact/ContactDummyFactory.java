package jp.kukv.user_api.domain.model.contact;

import jp.kukv.user_api.domain.model.contact.address.Address;

public class ContactDummyFactory {
  public static Contact create(String mailAddress, String phoneNumber, Address address) {
    return new Contact(new MailAddress(mailAddress), new PhoneNumber(phoneNumber), address);
  }
}
