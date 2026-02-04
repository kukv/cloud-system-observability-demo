package jp.kukv.user_api.infrastructure._configuration.logging;

import ch.qos.logback.access.common.spi.AccessEvent;
import ch.qos.logback.access.common.spi.IAccessEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import java.util.ArrayList;
import java.util.List;

public class LogbackAccessIgnoreFilter extends Filter<IAccessEvent> {

  public static final List<String> DENY_LIST = new ArrayList<>();

  static {
    DENY_LIST.add("health");
    DENY_LIST.add("metrics");
    DENY_LIST.add("actuator");
  }

  @Override
  public FilterReply decide(IAccessEvent event) {
    AccessEvent accessEvent = (AccessEvent) event;
    String requestURI = accessEvent.getRequestURI();

    boolean isDenyList = DENY_LIST.stream().anyMatch(requestURI::contains);

    if (isDenyList) {
      return FilterReply.DENY;
    }

    return FilterReply.ACCEPT;
  }
}
