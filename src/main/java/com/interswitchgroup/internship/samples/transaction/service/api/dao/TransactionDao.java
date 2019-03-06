package com.interswitchgroup.internship.samples.transaction.service.api.dao;

import com.interswitchgroup.internship.samples.transaction.service.api.models.Agent;
import com.interswitchgroup.internship.samples.transaction.service.api.models.Transaction;

public interface TransactionDao extends BaseDao<Transaction> {
	Transaction findByTransactionRef(String transactionRef);
}
