package jp.kukv.order_api.domain.model.user;

import jp.kukv.order_api.domain.model.user.contact.Contact;
import jp.kukv.order_api.domain.model.user.profile.Profile;

public class UserDummyFactory {
  public static User create(Profile profile, Contact contact) {
    return new User(profile, contact);
  }
}
