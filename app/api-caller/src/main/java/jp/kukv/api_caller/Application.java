package jp.kukv.api_caller;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import jp.kukv.api_caller.application.service.ApiCallService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@SpringBootApplication
@EnableScheduling
class Application implements SchedulingConfigurer {

  ApiCallService apiCallService;

  static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.addTriggerTask(
        apiCallService::call,
        triggerContext -> {
          long delayMs = ThreadLocalRandom.current().nextLong(10_000, 60_001); // 10〜60秒

          Date lastCompletionTime = triggerContext.lastCompletionTime();
          if (lastCompletionTime == null) {
            return Instant.now().plusMillis(delayMs);
          }

          Instant base = lastCompletionTime.toInstant();
          return base.plusMillis(delayMs);
        });
  }

  Application(ApiCallService apiCallService) {
    this.apiCallService = apiCallService;
  }
}
