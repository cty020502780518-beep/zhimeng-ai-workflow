package com.zhimeng.ai.console.commons.mapper.bot;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhimeng.ai.console.commons.dto.bot.BotFavoriteQueryDto;
import com.zhimeng.ai.console.commons.entity.bot.BotFavorite;
import com.zhimeng.ai.console.commons.dto.bot.ChatBotMarketPage;

import java.util.LinkedList;

public interface BotFavoriteMapper extends BaseMapper<BotFavorite> {

    LinkedList<ChatBotMarketPage> selectBotPage(BotFavoriteQueryDto queryDto);

    Long countBotPage(BotFavoriteQueryDto queryDto);

}
