package com.interswitchgroup.internship.samples.transaction.service.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interswitchgroup.internship.samples.transaction.service.api.dao.AgentDao;
import com.interswitchgroup.internship.samples.transaction.service.api.exception.RequestException;
import com.interswitchgroup.internship.samples.transaction.service.api.models.Agent;
import com.interswitchgroup.internship.samples.transaction.service.api.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService {
	@Autowired
	AgentDao agentDao;
	
	@Override
	public Agent createAgent(Agent agent) {
		return agentDao.create(agent);
	}

	@Override
	public boolean updateAgent(Long id, Agent agent) {
		Agent existingAgent = agentDao.find(id);
		if (existingAgent == null)
			throw new RequestException("Agent not found");
					
		agent.setId(id);
		return agentDao.update(agent);
	}

}
