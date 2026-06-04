package com.zhimeng.ai.console.hub.service.space;

import com.zhimeng.ai.console.commons.response.ApiResult;
import com.zhimeng.ai.console.commons.dto.space.InviteRecordAddDTO;
import com.zhimeng.ai.console.commons.enums.space.InviteRecordTypeEnum;
import com.zhimeng.ai.console.commons.dto.space.BatchChatUserVO;
import com.zhimeng.ai.console.commons.dto.space.ChatUserVO;
import com.zhimeng.ai.console.commons.dto.space.InviteRecordVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InviteRecordBizService {

    ApiResult<String> spaceInvite(List<InviteRecordAddDTO> dtos);

    ApiResult<String> enterpriseInvite(List<InviteRecordAddDTO> dtos);

    ApiResult<String> acceptInvite(Long inviteId);

    ApiResult<String> refuseInvite(Long inviteId);

    ApiResult<String> revokeEnterpriseInvite(Long inviteId);

    ApiResult<String> revokeSpaceInvite(Long inviteId);

    InviteRecordVO getRecordByParam(String param);

    List<ChatUserVO> searchUser(String mobile, InviteRecordTypeEnum type);

    List<ChatUserVO> searchUsername(String username, InviteRecordTypeEnum type);

    ApiResult<BatchChatUserVO> searchUserBatch(MultipartFile file);

    ApiResult<BatchChatUserVO> searchUsernameBatch(MultipartFile file);
}
