package jp.kukv.order_api.domain.model.user.profile;

public class ProfileDummyFactory {
  public static Profile create(String firstName, String lastName) {
    return new Profile(new FirstName(firstName), new LastName(lastName));
  }
}
