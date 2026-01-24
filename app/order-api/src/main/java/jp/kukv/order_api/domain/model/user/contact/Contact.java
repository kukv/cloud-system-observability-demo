package jp.kukv.order_api.domain.model.user.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.kukv.order_api.domain.model.user.contact.address.Address;

public class Contact {

  @JsonProperty MailAddress mailAddress;

  @JsonProperty PhoneNumber phoneNumber;

  @JsonProperty Address address;

  Contact(MailAddress mailAddress, PhoneNumber phoneNumber, Address address) {
    this.mailAddress = mailAddress;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public Address address() {
    return address;
  }

  @Override
  public String toString() {
    return "Contact{"
        + "mailAddress="
        + mailAddress
        + ", phoneNumber="
        + phoneNumber
        + ", address="
        + address
        + '}';
  }

  public Contact() {}
}
