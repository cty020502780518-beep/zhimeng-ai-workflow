package com.zhimeng.ai.console.hub.entity.maas;

import com.zhimeng.ai.console.commons.dto.bot.BotCreateForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaasDuplicate extends BotCreateForm {

    private Long maasId;

}
