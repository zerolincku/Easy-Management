package com.linck.management.common.handler.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.linck.management.common.model.constant.StateEnum;

import java.io.IOException;

/**
 * @author: linck
 * @create: 2021-11-29
 * @description 将 int 类型的 state 反序列化为 StateEnum 类型
 */
public class StateDeserializer extends JsonDeserializer<StateEnum> {
    @Override
    public StateEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return StateEnum.getStateEnum(jsonParser.getValueAsInt());
    }
}
