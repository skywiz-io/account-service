package com.poalim.openshift.transaction;

import com.poalim.openshift.account.Account;
import com.poalim.openshift.account.AccountService;
import com.poalim.openshift.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<Transaction> findByToAccountOrFromAccount(Account fromAccount, Account toAccount) {
        logger.debug("TransactionService-findByToAccountOrFromAccount: fromAccount={}, toAccount={}",
                fromAccount.toString(), toAccount.toString());
        return transactionRepository.findByToAccountOrFromAccount(fromAccount, toAccount).
                orElse(new ArrayList<>());
    }

    @Transactional
    public String makeTransaction(Integer fromAccountId, Integer toAccountId,
                                    BigDecimal amount, String description) {
        logger.debug("TransactionService-makeTransaction: from: {}, to: {}, amount: {}",
                new Object[] { fromAccountId, toAccountId, amount});

        accountService.withdrawalFunds(fromAccountId, amount);
        accountService.addFunds(toAccountId, amount);

        Transaction transaction = new Transaction();
        transaction.setFromAccount(accountService.findAccountById(fromAccountId));
        transaction.setToAccount(accountService.findAccountById(toAccountId));
        transaction.setAmount(amount);
        transaction.setDescription(description);

        return this.save(transaction).getId();
    }

    @Transactional
    private Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
