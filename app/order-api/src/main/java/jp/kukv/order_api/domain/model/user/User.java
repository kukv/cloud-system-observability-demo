package jp.kukv.order_api.domain.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.kukv.order_api.domain.model.user.contact.Contact;
import jp.kukv.order_api.domain.model.user.profile.Profile;

public class User {

  @JsonProperty Profile profile;

  @JsonProperty Contact contact;

  User(Profile profile, Contact contact) {
    this.profile = profile;
    this.contact = contact;
  }

  public Contact contact() {
    return contact;
  }

  @Override
  public String toString() {
    return "User{" + "profile=" + profile + ", contact=" + contact + '}';
  }

  User() {}
}
