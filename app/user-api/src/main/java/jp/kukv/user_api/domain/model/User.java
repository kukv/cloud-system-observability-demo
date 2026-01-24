package jp.kukv.user_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.kukv.user_api.domain.model.contact.Contact;
import jp.kukv.user_api.domain.model.profile.Profile;

public class User {

  @JsonProperty Profile profile;

  @JsonProperty Contact contact;

  User(Profile profile, Contact contact) {
    this.profile = profile;
    this.contact = contact;
  }

  @Override
  public String toString() {
    return "User{" + "profile=" + profile + ", contact=" + contact + '}';
  }

  User() {}
}
