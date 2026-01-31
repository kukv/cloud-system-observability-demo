package jp.kukv.order_api.domain.model.user.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {

  @JsonProperty("first_name")
  FirstName firstName;

  @JsonProperty("last_name")
  LastName lastName;

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
