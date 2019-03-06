package com.interswitchgroup.internship.samples.transaction.service.api.dao.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.interswitchgroup.internship.samples.transaction.service.api.dao.AbstractBaseDao;
import com.interswitchgroup.internship.samples.transaction.service.api.dao.AgentDao;
import com.interswitchgroup.internship.samples.transaction.service.api.dao.util.RowCountMapper;
import com.interswitchgroup.internship.samples.transaction.service.api.models.Agent;

@Repository
public class AgentDaoImpl extends AbstractBaseDao<Agent> implements AgentDao {
	protected SimpleJdbcCall findByCode;
	
	@Autowired
    @Override
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("create_agent").withReturnValue();
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("update_agent").withReturnValue();
        find = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_agent")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Agent.class));
        findAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_all_agents").returningResultSet(RESULT_COUNT, new RowCountMapper())
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Agent.class));
        findByCode = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_agent_by_code")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Agent.class));
    }

//    @Autowired
//    @Override
//    public void setReadOnlyDataSource(@Qualifier(value = "readOnlyDataSource") DataSource dataSource) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        find = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_agent")
//                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Agent.class));
//        findAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_all_agents").returningResultSet(RESULT_COUNT, new RowCountMapper())
//                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Agent.class));
//    }
	
	 public Agent findByCode(String agentCode) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("code", agentCode);
        Map<String, Object> m = findByCode.execute(in);
        List<Agent> list = (List<Agent>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
	 }

}
