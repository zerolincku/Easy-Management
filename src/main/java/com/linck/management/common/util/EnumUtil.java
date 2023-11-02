package com.linck.management.common.util;

import com.linck.management.common.exception.BizException;
import com.linck.management.common.model.enums.NameValueInterface;

/**
 * 枚举工具类
 *
 * @author linck
 */
public class EnumUtil {

    public static NameValueInterface getEnumByValue(Class<? extends NameValueInterface> clazz, Object value) {
        NameValueInterface[] enumConstants = clazz.getEnumConstants();
        for (NameValueInterface nameValue : enumConstants) {
            if (nameValue.getValue().equals(value)) {
                return nameValue;
            }
        }
        throw new BizException(clazz.getSimpleName() + " 无此value: " + value);
    }

    public static NameValueInterface getEnumByName(Class<? extends NameValueInterface> clazz, Object name) {
        NameValueInterface[] enumConstants = clazz.getEnumConstants();
        for (NameValueInterface nameValue : enumConstants) {
            if (nameValue.getName().equals(name)) {
                return nameValue;
            }
        }
        throw new BizException(clazz.getSimpleName() + " 无此name: " + name);
    }
}
