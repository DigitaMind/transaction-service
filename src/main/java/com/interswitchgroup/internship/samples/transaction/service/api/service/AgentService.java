package com.interswitchgroup.internship.samples.transaction.service.api.service;

import com.interswitchgroup.internship.samples.transaction.service.api.models.Agent;

public interface AgentService {
	public Agent createAgent(Agent agent);
	public boolean updateAgent(Long id, Agent agent); 
}
