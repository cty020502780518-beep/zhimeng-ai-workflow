package com.zhimeng.ai.console.commons.mapper.bot;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhimeng.ai.console.commons.dto.bot.ChatBotApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatBotApiMapper extends BaseMapper<ChatBotApi> {

    List<ChatBotApi> selectListWithVersion(@Param(value = "uid") String uid);

}
