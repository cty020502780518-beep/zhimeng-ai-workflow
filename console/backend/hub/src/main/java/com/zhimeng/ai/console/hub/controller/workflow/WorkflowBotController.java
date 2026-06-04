package com.zhimeng.ai.console.hub.controller.workflow;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zhimeng.ai.console.commons.constant.ResponseEnum;
import com.zhimeng.ai.console.commons.dto.bot.BotCreateForm;
import com.zhimeng.ai.console.commons.dto.bot.BotInfoDto;
import com.zhimeng.ai.console.commons.dto.workflow.WorkflowInputTypeDto;
import com.zhimeng.ai.console.commons.entity.bot.UserLangChainInfo;
import com.zhimeng.ai.console.commons.response.ApiResult;
import com.zhimeng.ai.console.commons.service.data.UserLangChainDataService;
import com.zhimeng.ai.console.commons.util.MaasUtil;
import com.zhimeng.ai.console.commons.util.RequestContextUtil;
import com.zhimeng.ai.console.hub.entity.WorkflowTemplateGroup;
import com.zhimeng.ai.console.hub.entity.maas.MaasDuplicate;
import com.zhimeng.ai.console.hub.entity.maas.MaasTemplate;
import com.zhimeng.ai.console.hub.entity.maas.WorkflowTemplateQueryDto;
import com.zhimeng.ai.console.hub.service.workflow.BotMaasService;
import com.zhimeng.ai.console.hub.service.workflow.WorkflowTemplateGroupService;
import com.zhimeng.ai.console.hub.util.BotPermissionUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Workflow related
 *
 * @author cherry
 */
@Slf4j
@Tag(name = "Workflow Assistant Interface")
@RestController
@RequestMapping(value = "/workflow/bot")
public class WorkflowBotController {

    @Autowired
    private WorkflowTemplateGroupService workflowTemplateGroupService;

    @Autowired
    private BotMaasService botMaasService;

    @Autowired
    private BotPermissionUtil botPermissionUtil;

    @Autowired
    private UserLangChainDataService userLangChainDataService;

    @Autowired
    private MaasUtil maasUtil;

    @GetMapping("/templateGroup")
    @Operation(summary = "work flow template", description = "Get workflow group information")
    public ApiResult<List<WorkflowTemplateGroup>> templateGroup(HttpServletRequest request) {
        // Interceptor performs login verification
        return ApiResult.success(workflowTemplateGroupService.getTemplateGroup());
    }

    @Operation(summary = "work flow template", description = "Create workflow assistant from template")
    @PostMapping("/createFromTemplate")
    @Transactional(rollbackFor = Exception.class)
    public ApiResult<BotInfoDto> createFromTemplate(HttpServletRequest request,
            @RequestBody MaasDuplicate maasDuplicate) {
        String uid = RequestContextUtil.getUID();
        return ApiResult.success(botMaasService.createFromTemplate(uid, maasDuplicate, request));
    }

    @PostMapping("/templateList")
    @Operation(summary = "work flow template", description = "Get workflow templates")
    public ApiResult<List<MaasTemplate>> templateList(HttpServletRequest request,
            @RequestBody WorkflowTemplateQueryDto queryDto) {
        return ApiResult.success(botMaasService.templateList(queryDto));
    }

    @PostMapping("/get-inputs-type")
    public ApiResult<List<WorkflowInputTypeDto>> getInputsType(HttpServletRequest request, @RequestBody BotCreateForm bot) {
        Integer botId = bot.getBotId();
        botPermissionUtil.checkBot(botId);
        List<UserLangChainInfo> chainInfo = userLangChainDataService.findListByBotId(botId);
        log.info("user long chain info:{}", JSON.toJSONString(chainInfo));
        if (chainInfo == null || chainInfo.isEmpty()) {
            return ApiResult.error(ResponseEnum.ACTIVITY_NOT_FOUND_ERROR);
        }
        String authorizationHeader = MaasUtil.getAuthorizationHeader(request);
        JSONObject data = maasUtil.getInputsType(botId, chainInfo.getFirst(), authorizationHeader);
        List<WorkflowInputTypeDto> args = data.getJSONArray("data").toJavaList(WorkflowInputTypeDto.class);
        return ApiResult.success(args);
    }
}
