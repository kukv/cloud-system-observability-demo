package jp.kukv.product_api.infrastructure._configuration.logging;

import ch.qos.logback.access.common.spi.IAccessEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogbackAccessIgnoreFilter extends Filter<IAccessEvent> {

  @Override
  public FilterReply decide(IAccessEvent event) {
    if (AccessDenyList.contains(event.getRequestURI())) {
      return FilterReply.DENY;
    }
    return FilterReply.ACCEPT;
  }
}
