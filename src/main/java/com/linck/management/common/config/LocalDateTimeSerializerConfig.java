package com.linck.management.common.config;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: linck
 * @create: 2021-12-07
 * @description Java8 时间类, Jackson 序列化配置类
 */
@Configuration
public class LocalDateTimeSerializerConfig {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Map<Class<?>, JsonSerializer<?>> serializers = new HashMap<>();
        serializers.put(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        serializers.put(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        serializers.put(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER));

        Map<Class<?>, JsonDeserializer<?>> deserializers = new HashMap<>();
        deserializers.put(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        deserializers.put(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
        deserializers.put(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));
        return builder -> builder.serializersByType(serializers).deserializersByType(deserializers);
    }
}
