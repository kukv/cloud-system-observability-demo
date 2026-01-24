package jp.kukv.user_api.domain.model.profile;

public class ProfileDummyFactory {
  public static Profile create(String firstName, String lastName) {
    return new Profile(new FirstName(firstName), new LastName(lastName));
  }
}
