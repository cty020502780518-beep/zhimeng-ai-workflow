package com.zhimeng.ai.console.hub.service.homepage.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhimeng.ai.console.commons.data.UserInfoDataService;
import com.zhimeng.ai.console.commons.entity.bot.ChatBotMarket;
import com.zhimeng.ai.console.commons.entity.chat.ChatList;
import com.zhimeng.ai.console.commons.service.bot.BotFavoriteService;
import com.zhimeng.ai.console.commons.service.bot.BotTypeListService;
import com.zhimeng.ai.console.commons.service.bot.ChatBotMarketService;
import com.zhimeng.ai.console.commons.service.data.ChatListDataService;
import com.zhimeng.ai.console.commons.util.RequestContextUtil;
import com.zhimeng.ai.console.hub.dto.homepage.BotInfoDto;
import com.zhimeng.ai.console.hub.dto.homepage.BotListPageDto;
import com.zhimeng.ai.console.hub.dto.homepage.BotTypeDto;
import com.zhimeng.ai.console.hub.service.homepage.AgentSquareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yun-zhi-ztl
 */
@Service
@Slf4j
public class AgentSquareServiceImpl implements AgentSquareService {

    @Autowired
    private BotTypeListService botTypeListService;

    @Autowired
    private ChatBotMarketService chatBotMarketService;

    @Autowired
    private UserInfoDataService userInfoDataService;

    @Autowired
    private BotFavoriteService botFavoriteService;

    @Autowired
    private ChatListDataService chatListDataService;

    @Override
    public List<BotTypeDto> getBotTypeList() {
        return botTypeListService.getBotTypeList()
                .stream()
                .map(item -> new BotTypeDto(
                        item.getTypeKey(),
                        item.getTypeName(),
                        item.getIcon(),
                        item.getTypeNameEn()))
                .toList();
    }

    @Override
    public BotListPageDto getBotPageByType(Integer type, String search, Integer pageSize, Integer page) {
        // Get paginated assistant list
        Page<ChatBotMarket> marketPage = chatBotMarketService.getBotPage(type, search, pageSize, page);
        // Get current user's UID
        String uid;
        Set<Integer> favoriteIds = new HashSet<>();
        try {
            uid = RequestContextUtil.getUID();
            if (uid != null && !uid.isEmpty()) {
                favoriteIds = new HashSet<>(botFavoriteService.list(uid));
            }
        } catch (Exception e) {
            uid = null;
        }

        // Use Stream to process each assistant, convert to DTO
        String finalUid = uid;
        Set<Integer> finalFavoriteIds = favoriteIds;
        List<BotInfoDto> botInfoList = marketPage.getRecords()
                .stream()
                .map(market -> {
                    String creatorName = userInfoDataService.findNickNameByUid(market.getUid()).orElse(null);
                    ChatList latestChat;
                    Long chatId = null;
                    if (finalUid != null && !finalUid.isEmpty()) {
                        latestChat = chatListDataService.findLatestEnabledChatByUserAndBot(finalUid, market.getBotId());
                        chatId = latestChat != null ? latestChat.getId() : null;
                    }
                    return new BotInfoDto(
                            market.getBotId(),
                            chatId,
                            market.getBotName(),
                            type,
                            market.getAvatar(),
                            market.getPrompt(),
                            market.getBotDesc(),
                            finalFavoriteIds.contains(market.getBotId()),
                            creatorName,
                            market.getVersion());
                })
                .collect(Collectors.toList());
        return new BotListPageDto(
                botInfoList,
                Math.toIntExact(marketPage.getTotal()),
                Math.toIntExact(marketPage.getSize()),
                Math.toIntExact(marketPage.getCurrent()),
                Math.toIntExact(marketPage.getPages()));
    }
}
