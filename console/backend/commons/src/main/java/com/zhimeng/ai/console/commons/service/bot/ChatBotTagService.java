package com.zhimeng.ai.console.commons.service.bot;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhimeng.ai.console.commons.entity.bot.ChatBotTag;

import java.util.List;

public interface ChatBotTagService extends IService<ChatBotTag> {

    /**
     * Pass in botId and return the corresponding array for botId
     *
     * @param botId
     * @return
     */
    List<String> getBotTagList(Long botId);

    /**
     * Displayed when the assistant is submitted for review, update the latest tags
     */
    void updateTags(Long botId);

}
