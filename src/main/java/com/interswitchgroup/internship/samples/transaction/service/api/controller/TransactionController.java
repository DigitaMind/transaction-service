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
import com.interswitchgroup.internship.samples.transaction.service.api.dao.TransactionDao;
import com.interswitchgroup.internship.samples.transaction.service.api.exception.RequestException;
import com.interswitchgroup.internship.samples.transaction.service.api.models.Agent;
import com.interswitchgroup.internship.samples.transaction.service.api.models.Transaction;
import com.interswitchgroup.internship.samples.transaction.service.api.utils.Constant;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
//	private final TransactionRepository transactionRepository;
//	
//	public TransactionController(TransactionRepository transactionRepository) {
//		this.transactionRepository = transactionRepository;
//	}
//	
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody
//	@ResponseStatus(HttpStatus.CREATED)
//	public Transaction createTransaction(@RequestBody @Validated final Transaction transaction) {
//		return transactionRepository.save(transaction);
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	@ResponseStatus(HttpStatus.OK)
//	public Transaction updateTransaction(@PathVariable( "id" ) Integer id, @RequestBody @Validated final Transaction transaction) {
//		return transactionRepository.save(transaction);
//	}
	
	private final TransactionDao transactionDao;
	private final AgentDao agentDao;
	
	public TransactionController(TransactionDao transactionDao, AgentDao agentDao) {
		this.transactionDao = transactionDao;
		this.agentDao = agentDao;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Transaction createTransaction(@RequestBody @Validated final Transaction transaction) {
		Agent agent = agentDao.findByCode(transaction.getAgentCode());
		if (agent == null)
			throw new RequestException("Agent does not exist");
		
		double fee = 0.0;
		if (Constant.AGENT_TYPE_PREMIUM.equalsIgnoreCase(Integer.toString(agent.getCategory()))){
			fee = Double.valueOf(transaction.getAmount()) * Constant.PREMIUM_FEE_PERCENT / 100.0;
		} else {
			fee = Double.valueOf(transaction.getAmount()) * Constant.CLASSIC_FEE_PERCENT / 100.0;
		}
			
		transaction.setFee(String.valueOf(fee));
		return transactionDao.create(transaction);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public boolean updateTransaction(@PathVariable( "id" ) Integer id, @RequestBody @Validated final Transaction transaction) {
		return transactionDao.update(transaction);
	}
	
	@RequestMapping(value = "/{transactionRef}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Transaction findTransaction(@PathVariable( "transactionRef" ) String transactionRef) {
		Transaction transaction = transactionDao.findByTransactionRef(transactionRef);
		return transaction;
	}
}
