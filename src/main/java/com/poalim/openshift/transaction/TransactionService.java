package com.poalim.openshift.transaction;

import com.poalim.openshift.account.Account;
import com.poalim.openshift.account.AccountService;
import com.poalim.openshift.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by osher on 20/7/17.
 */

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction findById(String id) {
        logger.debug("TransactionService-findById: id={}", id);
        return transactionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Transaction not found:" + id));
    }

    public List<Transaction> findByFromAccount(Account account) {
        logger.debug("TransactionService-findByFromAccount: account={}", account.toString());
        return transactionRepository.findByFromAccount(account).
                orElse(new ArrayList<>());
    }

    public List<Transaction> findByToAccount(Account account) {
        logger.debug("TransactionService-findByToAccount: account={}", account.toString());
        return transactionRepository.findByToAccount(account).
                orElse(new ArrayList<>());
    }

    public List<Transaction> findByToAccountOrFromAccount(Account fromAccount, Account toAccount) {
        logger.debug("TransactionService-findByToAccountOrFromAccount: fromAccount={}, toAccount={}",
                fromAccount.toString(), toAccount.toString());
        return transactionRepository.findByToAccountOrFromAccount(fromAccount, toAccount).
                orElse(new ArrayList<>());
    }

    public String makeTransacition(Integer fromAccount, Integer toAccount,
                                    BigDecimal amount, String description) {
        logger.debug("TransactionService-makeTransacition: from: {}, to: {}, amount: {}",
                new Object[] { fromAccount, toAccount, amount});

        Transaction transaction = new Transaction();
        transaction.setFromAccount(accountService.findAccountById(fromAccount));
        transaction.setToAccount(accountService.findAccountById(toAccount));
        transaction.setAmount(amount);
        transaction.setDescription(description);
        return this.save(transaction).getId();
    }

    private Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
