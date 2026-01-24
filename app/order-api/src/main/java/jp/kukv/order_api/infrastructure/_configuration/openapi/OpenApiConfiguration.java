package jp.kukv.order_api.infrastructure._configuration.openapi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfiguration {

  static {
    Schema<LocalDateTime> localDateTimeSchema = new Schema<>();
    localDateTimeSchema.example(
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    SpringDocUtils.getConfig().replaceWithSchema(LocalDateTime.class, localDateTimeSchema);
  }

  @Bean
  OpenAPI openAPI() {
    OpenAPI openAPI = new OpenAPI();
    openAPI.setComponents(new Components());
    openAPI.setInfo(info());
    openAPI.servers(servers());

    return openAPI;
  }

  private Info info() {
    Contact contact = new Contact();
    contact.setName("koki nonaka");
    contact.setEmail("koki-nonaka@outlook.jp");
    contact.setUrl("https://bright-room.net");

    Info info = new Info();
    info.setTitle("order api");
    info.setDescription("Order API");
    info.setContact(contact);
    info.setVersion("0.0.1");
    return info;
  }

  private List<Server> servers() {
    List<Server> servers = new ArrayList<>();

    Server localServer = new Server();
    localServer.setUrl("http://localhost:8082");
    localServer.setDescription("Local");
    servers.add(localServer);

    return servers;
  }

  @Bean
  ModelResolver modelResolver() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NON_PRIVATE);
    objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
    objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    JavaTimeModule module = new JavaTimeModule();
    module.addSerializer(new LocalDateSerializer(DateTimeFormatter.ISO_DATE));
    module.addSerializer(
        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    module.addSerializer(new YearMonthSerializer(DateTimeFormatter.ofPattern("yyyy-MM")));

    module.addDeserializer(
        LocalDateTime.class,
        new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    objectMapper.registerModule(module);

    return new ModelResolver(objectMapper);
  }
}
