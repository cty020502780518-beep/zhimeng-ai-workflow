package com.zhimeng.ai.console.toolkit.mapper.group;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhimeng.ai.console.toolkit.entity.table.group.GroupVisibility;
import com.zhimeng.ai.console.toolkit.entity.vo.group.GroupUserTagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper interface
 * </p>
 *
 * @author xxzhang23
 * @since 2024-01-08
 */

@Mapper

public interface GroupVisibilityMapper extends BaseMapper<GroupVisibility> {

    List<GroupUserTagVO> listUser(@Param("uid") String uid, @Param("type") Long type, @Param("id") Long id);

    List<GroupVisibility> getRepoVisibilityList(@Param("uid") String userId, @Param("spaceId") Long spaceId);

    List<GroupVisibility> getToolVisibilityList(@Param("uid") String userId);

    List<GroupVisibility> getSquareToolVisibilityList(@Param("uid") String userId);
}
