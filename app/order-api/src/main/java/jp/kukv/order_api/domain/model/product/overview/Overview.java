package jp.kukv.order_api.domain.model.product.overview;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Overview {

  @JsonProperty Name name;

  @JsonProperty Description description;

  Overview(Name name, Description description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Overview{" + "name=" + name + ", description=" + description + '}';
  }

  Overview() {}
}
