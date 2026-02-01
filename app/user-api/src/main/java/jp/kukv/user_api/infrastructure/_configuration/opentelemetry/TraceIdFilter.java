package jp.kukv.user_api.infrastructure._configuration.opentelemetry;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

/** Step 6: Add Trace ID to HTTP Response Headers */
@Component
class TraceIdFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    if (response instanceof HttpServletResponse httpResponse) {
      String traceId = Span.current().getSpanContext().getTraceId();
      httpResponse.setHeader("X-Trace-Id", traceId);
    }

    chain.doFilter(request, response);
  }
}
