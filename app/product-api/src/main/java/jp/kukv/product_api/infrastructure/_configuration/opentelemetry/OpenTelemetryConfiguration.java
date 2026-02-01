package jp.kukv.product_api.infrastructure._configuration.opentelemetry;

import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmClassLoadingMeterConventions;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmCpuMeterConventions;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmMemoryMeterConventions;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmThreadMeterConventions;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.observation.OpenTelemetryServerRequestObservationConvention;

/** Step 2.2: Configure OpenTelemetry Semantic Conventions */
@Configuration
class OpenTelemetryConfiguration {

  @Bean
  OpenTelemetryServerRequestObservationConvention
      openTelemetryServerRequestObservationConvention() {
    return new OpenTelemetryServerRequestObservationConvention();
  }

  @Bean
  OpenTelemetryJvmCpuMeterConventions openTelemetryJvmCpuMeterConventions() {
    return new OpenTelemetryJvmCpuMeterConventions(Tags.empty());
  }

  @Bean
  ProcessorMetrics processorMetrics() {
    return new ProcessorMetrics(List.of(), new OpenTelemetryJvmCpuMeterConventions(Tags.empty()));
  }

  @Bean
  JvmMemoryMetrics jvmMemoryMetrics() {
    return new JvmMemoryMetrics(
        List.of(), new OpenTelemetryJvmMemoryMeterConventions(Tags.empty()));
  }

  @Bean
  JvmThreadMetrics jvmThreadMetrics() {
    return new JvmThreadMetrics(
        List.of(), new OpenTelemetryJvmThreadMeterConventions(Tags.empty()));
  }

  @Bean
  ClassLoaderMetrics classLoaderMetrics() {
    return new ClassLoaderMetrics(new OpenTelemetryJvmClassLoadingMeterConventions());
  }

  /** Step 5: Context Propagation for Async Operations */
  //  @Bean
  //  ContextPropagatingTaskDecorator contextPropagatingTaskDecorator() {
  //    return new ContextPropagatingTaskDecorator();
  //  }
}
