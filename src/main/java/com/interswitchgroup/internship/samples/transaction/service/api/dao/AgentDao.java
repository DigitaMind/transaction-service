package com.interswitchgroup.internship.samples.transaction.service.api.dao;

import com.interswitchgroup.internship.samples.transaction.service.api.models.Agent;

public interface AgentDao extends BaseDao<Agent> {
	Agent findByCode(String code);
}
