package com.linck.management.common.config;

import cn.hutool.core.util.ClassUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.linck.management.common.model.enums.NameValueInterface;
import com.linck.management.common.util.EnumUtil;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Jackson 序列化配置类
 * @author linck
 */
@Configuration
public class JacksonConfig {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Map<Class<?>, JsonSerializer<?>> serializers = new HashMap<>();
        serializers.put(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        serializers.put(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        serializers.put(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER));

        // NameValue 序列化配置
        serializers.put(NameValueInterface.class, new JsonSerializer<NameValueInterface>() {
            @Override
            public void serialize(NameValueInterface value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
                jsonGenerator.writeNumber(value.getValue());
            }
        });

        Map<Class<?>, JsonDeserializer<?>> deserializers = new HashMap<>();
        deserializers.put(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        deserializers.put(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
        deserializers.put(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));

        // 扫描当前项目下所有 NameValueInterface 实现类
        Set<Class<?>> classes = ClassUtil.scanPackage("com.linck.forum"
                , aClass -> NameValueInterface.class.isAssignableFrom(aClass) && !NameValueInterface.class.equals(aClass));

        // 自动注册枚举反序列化规则
        classes.forEach(clazz -> deserializers.put(clazz, new JsonDeserializer() {
            @Override
            public Object deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
                int intValue = jsonParser.getIntValue();
                return EnumUtil.getEnumByValue((Class<? extends NameValueInterface>) clazz, intValue);
            }
        }));

        return builder -> builder.serializersByType(serializers).deserializersByType(deserializers);
    }
}
