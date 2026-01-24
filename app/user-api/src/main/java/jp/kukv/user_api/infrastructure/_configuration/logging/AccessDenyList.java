package jp.kukv.user_api.infrastructure._configuration.logging;

import java.util.Set;

class AccessDenyList {

  static final Set<String> list;

  static {
    list = Set.of("/actuator/health/liveness", "/actuator/health/readiness", "/favicon.ico");
  }

  static boolean contains(String value) {
    return list.contains(value);
  }
}
