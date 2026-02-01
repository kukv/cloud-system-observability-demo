package jp.kukv.order_api.infrastructure._configuration.opentelemetry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.ContextPropagatingTaskDecorator;

/** Step 5: Context Propagation for Async Operations */
@Configuration(proxyBeanMethods = false)
class ContextPropagationConfiguration {

  @Bean
  ContextPropagatingTaskDecorator contextPropagatingTaskDecorator() {
    return new ContextPropagatingTaskDecorator();
  }
}
