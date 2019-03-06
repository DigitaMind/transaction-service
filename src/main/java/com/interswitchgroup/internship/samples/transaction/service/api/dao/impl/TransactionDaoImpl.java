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
import com.interswitchgroup.internship.samples.transaction.service.api.dao.TransactionDao;
import com.interswitchgroup.internship.samples.transaction.service.api.dao.util.RowCountMapper;
import com.interswitchgroup.internship.samples.transaction.service.api.models.Agent;
import com.interswitchgroup.internship.samples.transaction.service.api.models.Transaction;

@Repository
public class TransactionDaoImpl extends AbstractBaseDao<Transaction> implements TransactionDao {
	protected SimpleJdbcCall findByTransactionRef;
	
	@Autowired
    @Override
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("create_transaction").withReturnValue();
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("update_transaction").withReturnValue();
        find = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_transaction")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Transaction.class));
        findAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_all_transactions").returningResultSet(RESULT_COUNT, new RowCountMapper())
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Transaction.class));
        findByTransactionRef = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_transaction_by_trans_ref")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Transaction.class));
    }

//    @Autowired
//    @Override
//    public void setReadOnlyDataSource(@Qualifier(value = "readOnlyDataSource") DataSource dataSource) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        find = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_transaction")
//                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Transaction.class));
//        findAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_all_transactions").returningResultSet(RESULT_COUNT, new RowCountMapper())
//                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Transaction.class));
//    }
	
	public Transaction findByTransactionRef(String transactionRef) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("transaction_ref", transactionRef);
        Map<String, Object> m = findByTransactionRef.execute(in);
        List<Transaction> list = (List<Transaction>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
	 }
	
	;

}
