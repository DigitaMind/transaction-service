package com.interswitchgroup.internship.samples.transaction.service.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.interswitchgroup.internship.samples.transaction.service.api.dao.AgentDao;
import com.interswitchgroup.internship.samples.transaction.service.api.models.Agent;
import com.interswitchgroup.internship.samples.transaction.service.api.repository.AgentRepository;

@Controller
@RequestMapping("/agents")
public class AgentController {
//	private final AgentRepository agentRepository;
//	
//	public AgentController(AgentRepository agentRepository) {
//		this.agentRepository = agentRepository;
//	}
//	
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody
//	@ResponseStatus(HttpStatus.CREATED)
//	public Agent createAgent(@RequestBody @Validated final Agent agent) {
//		return agentRepository.save(agent);
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	@ResponseStatus(HttpStatus.OK)
//	public Agent updateAgent(@PathVariable( "id" ) Integer id, @RequestBody @Validated final Agent agent) {
//		return agentRepository.save(agent);
//	}
	
	private final AgentDao agentDao;
	
	public AgentController(AgentDao agentDao) {
		this.agentDao = agentDao;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Agent createAgent(@RequestBody @Validated final Agent agent) {
		return agentDao.create(agent);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public boolean updateAgent(@PathVariable( "id" ) Integer id, @RequestBody @Validated final Agent agent) {
		return agentDao.update(agent);
	}
	
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Agent findAgent(@PathVariable( "code" ) String code) {
		Agent agent = agentDao.findByCode(code);
		return agent;
	}
}
