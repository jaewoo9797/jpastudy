package com.jaewoo.blogdemo.common.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JacksonConfig {

    public static final JsonSerializer<LocalDateTime> LOCAL_DATE_TIME_SERIALIZER =
            new JsonSerializer<>() {
                @Override
                public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
                                      SerializerProvider serializerProvider) throws IOException {
                    jsonGenerator.writeString(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
            };

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .serializers(LOCAL_DATE_TIME_SERIALIZER)
                .serializationInclusion(Include.NON_NULL)
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        return new MappingJackson2HttpMessageConverter(builder.build());
    }
}
