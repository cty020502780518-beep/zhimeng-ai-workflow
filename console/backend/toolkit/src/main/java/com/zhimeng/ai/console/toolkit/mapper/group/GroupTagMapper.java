package com.zhimeng.ai.console.toolkit.mapper.group;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhimeng.ai.console.toolkit.entity.table.group.GroupTag;
import com.zhimeng.ai.console.toolkit.entity.vo.group.GroupTagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

public interface GroupTagMapper extends BaseMapper<GroupTag> {
    List<GroupTagVO> listGroupTagVOByUid(@Param("uid") String uid);
}
