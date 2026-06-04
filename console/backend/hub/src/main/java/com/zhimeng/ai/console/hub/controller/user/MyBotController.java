package com.zhimeng.ai.console.hub.controller.user;

import com.zhimeng.ai.console.commons.annotation.space.SpacePreAuth;
import com.zhimeng.ai.console.commons.dto.bot.BotModelDto;
import com.zhimeng.ai.console.commons.dto.bot.BotDetail;
import com.zhimeng.ai.console.commons.dto.bot.PromptBotDetail;
import com.zhimeng.ai.console.commons.response.ApiResult;
import com.zhimeng.ai.console.commons.service.bot.ChatBotDataService;
import com.zhimeng.ai.console.commons.util.RequestContextUtil;
import com.zhimeng.ai.console.hub.dto.user.MyBotPageDTO;
import com.zhimeng.ai.console.hub.dto.user.MyBotParamDTO;
import com.zhimeng.ai.console.hub.service.chat.ChatListService;
import com.zhimeng.ai.console.hub.service.user.UserBotService;
import com.zhimeng.ai.console.hub.util.BotPermissionUtil;
import com.zhimeng.ai.console.toolkit.service.repo.MassDatasetInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author wowo
 * @since 2025/9/9 15:24
 **/

@Slf4j
@Controller
@RequestMapping("/my-bot")
@Tag(name = "Personal agent correlation")
@RestController
public class MyBotController {

    @Autowired
    private UserBotService userBotService;

    @Autowired
    private ChatBotDataService chatBotDataService;

    @Autowired
    private BotPermissionUtil botPermissionUtil;

    @Autowired
    private MassDatasetInfoService massDatasetInfoService;

    @Autowired
    private ChatListService chatListService;

    /**
     * Display assistants I created
     */
    @SpacePreAuth(key = "MyBotController_getCreatedList_POST")
    @PostMapping("/list")
    @Operation(summary = "User-created assistant presentation")
    public ApiResult<MyBotPageDTO> getCreatedList(@RequestBody MyBotParamDTO myBotParamDTO) {
        return ApiResult.success(userBotService.listMyBots(myBotParamDTO));
    }

    /**
     * Delete assistant
     */
    @SpacePreAuth(key = "MyBotController_deleteBot_POST")
    @PostMapping("/delete")
    @Operation(summary = "User-created assistant deletion")
    public ApiResult<Boolean> deleteBot(@RequestParam(value = "botId") Integer botId) {
        return ApiResult.success(userBotService.deleteBot(botId));
    }

    /**
     * Get bot detail information
     */
    @SpacePreAuth(key = "MyBotController_getBotDetail_POST")
    @PostMapping("/bot-detail")
    @Operation(summary = "Get bot detail information")
    public ApiResult<BotDetail> getBotDetail(HttpServletRequest request, @RequestParam("botId") Integer botId) {
        // Permission validation
        botPermissionUtil.checkBot(botId);
        String uid = RequestContextUtil.getUID();

        // Get bot detail data
        PromptBotDetail botDetail = chatBotDataService.getPromptBotDetail(botId, uid);
        botDetail.setMaasDatasetList(massDatasetInfoService.getDatasetMaasByBot(uid, botId, request));

        // Manually parse inputExample to inputExampleList
        botDetail.parseInputExampleList();

        // Return model information, if modelId is empty, it indicates default model
        BotModelDto botModelDto = chatListService.getBotModelDto(request, botDetail.getModelId(), botDetail.getModel());
        botDetail.setBotModel(botModelDto);

        return ApiResult.success(botDetail);
    }

}
