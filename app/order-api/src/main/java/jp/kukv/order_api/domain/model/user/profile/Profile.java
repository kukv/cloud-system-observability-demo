package jp.kukv.order_api.domain.model.user.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {

  @JsonProperty FirstName firstName;

  @JsonProperty LastName lastName;

  Profile(FirstName firstName, LastName lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "Profile{" + "firstName=" + firstName + ", lastName=" + lastName + '}';
  }

  Profile() {}
}
