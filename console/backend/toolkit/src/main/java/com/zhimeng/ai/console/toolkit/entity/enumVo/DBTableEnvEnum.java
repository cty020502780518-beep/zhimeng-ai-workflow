package com.zhimeng.ai.console.toolkit.entity.enumVo;


import com.zhimeng.ai.console.commons.constant.ResponseEnum;
import com.zhimeng.ai.console.commons.exception.BusinessException;

import java.util.Objects;

/**
 * Database operation enumeration
 */
public enum DBTableEnvEnum {

    TEST(1, "test"),
    PROD(2, "prod");

    private Integer code;
    private String value;

    DBTableEnvEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String getByCode(Integer code) {
        for (DBTableEnvEnum envEnum : DBTableEnvEnum.values()) {
            if (Objects.equals(envEnum.getCode(), code)) {
                return envEnum.getValue();
            }
        }
        throw new BusinessException(ResponseEnum.RESPONSE_FAILED, "Related enumeration class not found");
    }
}
