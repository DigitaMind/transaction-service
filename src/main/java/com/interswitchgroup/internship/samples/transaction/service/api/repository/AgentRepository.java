package com.interswitchgroup.internship.samples.transaction.service.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import com.interswitchgroup.internship.samples.transaction.service.api.models.Agent;

public interface AgentRepository extends CrudRepository<Agent, Integer> {

}
