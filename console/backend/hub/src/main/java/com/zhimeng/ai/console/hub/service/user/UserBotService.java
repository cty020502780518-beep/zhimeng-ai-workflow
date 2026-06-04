package com.zhimeng.ai.console.hub.service.user;

import com.zhimeng.ai.console.hub.dto.user.MyBotPageDTO;
import com.zhimeng.ai.console.hub.dto.user.MyBotParamDTO;


/**
 * @author wowo_zZ
 * @since 2025/9/9 19:23
 **/

public interface UserBotService {

    MyBotPageDTO listMyBots(MyBotParamDTO myBotParamDTO);

    Boolean deleteBot(Integer botId);
}
