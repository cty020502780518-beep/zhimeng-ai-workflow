package com.zhimeng.ai.console.toolkit.entity.biz.external.app;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlatformAppDetail extends PlatformApp {
    Integer abilityCount;
    String apiKey;
    String apiSecret;
}
