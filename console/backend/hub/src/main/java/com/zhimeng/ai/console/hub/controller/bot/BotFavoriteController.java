package com.zhimeng.ai.console.hub.controller.bot;

import com.zhimeng.ai.console.commons.dto.bot.BotFavoritePageDto;
import com.zhimeng.ai.console.commons.dto.bot.BotMarketForm;
import com.zhimeng.ai.console.commons.response.ApiResult;
import com.zhimeng.ai.console.commons.service.bot.BotFavoriteService;
import com.zhimeng.ai.console.commons.util.I18nUtil;
import com.zhimeng.ai.console.commons.util.RequestContextUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Assistant Favorites")
@RestController
@RequestMapping(value = "/bot/favorite")
public class BotFavoriteController {

    @Autowired
    private BotFavoriteService botFavoriteService;

    @PostMapping(value = "/list")
    public ApiResult<BotFavoritePageDto> list(HttpServletRequest request, @RequestBody BotMarketForm botMarketForm) {
        String uid = RequestContextUtil.getUID();
        String langCode = I18nUtil.getLanguage();
        BotFavoritePageDto pageDto = botFavoriteService.selectPage(botMarketForm, uid, langCode);
        return ApiResult.success(pageDto);
    }

    @PostMapping(value = "/create")
    public ApiResult<Void> create(@RequestParam Integer botId) {
        String uid = RequestContextUtil.getUID();
        botFavoriteService.create(uid, botId);

        return ApiResult.success();
    }

    @PostMapping(value = "/delete")
    public ApiResult<Void> delete(@RequestParam Integer botId) {
        String uid = RequestContextUtil.getUID();
        botFavoriteService.delete(uid, botId);

        return ApiResult.success();
    }
}
