package com.goryaninaa.web.bank.winter.dao.concurrent.stub;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.winter.repository.transaction.TransactionDAO;

public class TransactionDAOConcurrentStub implements TransactionDAO {
	
	private static int idCounter = 1;
	private final List<Operation> transactions;

	public TransactionDAOConcurrentStub() {
		this.transactions = new ArrayList<>();
	}

	@Override
	public void save(Operation transaction) {
		for (Operation savedEarlierTransaction : transactions) {
			if (savedEarlierTransaction.equals(transaction)) {
				throw new RuntimeException("This transaction already exists");
			}
		}
		
		transaction.setId(idCounter++);
		
		transactions.add(transaction);
	}

	@Override
	public List<Operation> findTransactionsOfAccount(int accountId) {
		return transactions.stream().filter(t -> t.getAccount().getId() == accountId).collect(Collectors.toList());
	}
}