package com.zhimeng.ai.console.hub.service.workflow;


import com.zhimeng.ai.console.commons.dto.bot.BotInfoDto;
import com.zhimeng.ai.console.commons.dto.workflow.CloneSynchronize;
import com.zhimeng.ai.console.hub.entity.maas.MaasDuplicate;
import com.zhimeng.ai.console.hub.entity.maas.MaasTemplate;
import com.zhimeng.ai.console.hub.entity.maas.WorkflowTemplateQueryDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public interface BotMaasService {
    BotInfoDto createFromTemplate(String uid, MaasDuplicate massDuplicate, HttpServletRequest request);

    Integer maasCopySynchronize(CloneSynchronize synchronize);

    List<MaasTemplate> templateList(WorkflowTemplateQueryDto queryDto);
}
