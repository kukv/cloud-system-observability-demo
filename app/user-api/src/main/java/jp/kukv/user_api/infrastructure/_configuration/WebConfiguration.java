package jp.kukv.user_api.infrastructure._configuration;

import ch.qos.logback.access.tomcat.LogbackValve;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

@Configuration
class WebConfiguration implements WebMvcConfigurer {

  @Primary
  @Bean
  JsonMapper jsonMapper() {
    SimpleModule datetimeModule = new SimpleModule();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    LocalDateTimeSerializer localDateTimeSerializer =
        new LocalDateTimeSerializer(dateTimeFormatter);
    datetimeModule.addSerializer(localDateTimeSerializer);

    LocalDateTimeDeserializer localDateTimeDeserializer =
        new LocalDateTimeDeserializer(dateTimeFormatter);
    datetimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);

    return JsonMapper.builder()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
        .enable(SerializationFeature.INDENT_OUTPUT)
        .changeDefaultVisibility(
            handler -> {
              handler.withFieldVisibility(JsonAutoDetect.Visibility.ANY);
              handler.withGetterVisibility(JsonAutoDetect.Visibility.NONE);
              handler.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
              handler.withCreatorVisibility(JsonAutoDetect.Visibility.ANY);

              return handler;
            })
        .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .addModule(datetimeModule)
        .build();
  }

  @Profile("!default")
  @Bean
  TomcatServletWebServerFactory servletContainer() {
    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
    tomcat.addContextValves(new LogbackValve());
    return tomcat;
  }
}
