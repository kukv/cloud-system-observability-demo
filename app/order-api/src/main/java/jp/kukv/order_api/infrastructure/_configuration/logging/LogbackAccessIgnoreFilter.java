package jp.kukv.order_api.infrastructure._configuration.logging;

import ch.qos.logback.access.common.spi.AccessEvent;
import ch.qos.logback.access.common.spi.IAccessEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogbackAccessIgnoreFilter extends Filter<IAccessEvent> {
  @Override
  public FilterReply decide(IAccessEvent event) {
    AccessEvent accessEvent = (AccessEvent) event;
    String requestURI = accessEvent.getRequestURI();

    boolean isDenyList = AccessDenyList.ACCESS_DENY_LIST.stream().anyMatch(requestURI::contains);

    if (isDenyList) {
      return FilterReply.DENY;
    }

    return FilterReply.ACCEPT;
  }
}
