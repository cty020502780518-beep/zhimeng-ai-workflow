package com.zhimeng.ai.console.hub.data;

import com.zhimeng.ai.console.commons.entity.space.AgentShareRecord;

public interface ShareDataService {

    /**
     * Find active sharing records based on user ID, sharing type, and associated ID
     */
    AgentShareRecord findActiveShareRecord(String uid, int shareType, Long baseId);

    /**
     * Create a new sharing record
     */
    AgentShareRecord createShareRecord(String uid, Long baseId, String shareKey, int shareType);

    /**
     * Find active sharing records based on the sharing key
     */
    AgentShareRecord findByShareKey(String shareKey);
}
