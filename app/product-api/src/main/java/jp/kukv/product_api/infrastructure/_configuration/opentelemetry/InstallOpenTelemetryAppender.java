package jp.kukv.product_api.infrastructure._configuration.opentelemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/** Step 4.4: Install OpenTelemetry Appender */
@Component
class InstallOpenTelemetryAppender implements InitializingBean {

  OpenTelemetry openTelemetry;

  @Override
  public void afterPropertiesSet() {
    OpenTelemetryAppender.install(this.openTelemetry);
  }

  InstallOpenTelemetryAppender(OpenTelemetry openTelemetry) {
    this.openTelemetry = openTelemetry;
  }
}
