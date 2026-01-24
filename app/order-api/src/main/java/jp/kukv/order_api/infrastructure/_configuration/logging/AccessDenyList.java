package jp.kukv.order_api.infrastructure._configuration.logging;

import java.util.ArrayList;
import java.util.List;

public class AccessDenyList {

  public static final List<String> ACCESS_DENY_LIST = new ArrayList<>();

  static {
    ACCESS_DENY_LIST.add("health");
    ACCESS_DENY_LIST.add("metrics");
    ACCESS_DENY_LIST.add("actuator");
  }
}
