package com.poalim.openshift.transaction;

import com.poalim.openshift.account.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by osher on 20/7/17.
 */
public interface TransactionRepository extends CrudRepository<Transaction, String> {
    Optional<Transaction> findById(String id);
    Optional<List<Transaction>> findByFromAccount(Account accountId);
    Optional<List<Transaction>> findByToAccount(Account accountId);
    Optional<List<Transaction>> findByToAccountOrFromAccount(Account fromAccount, Account toAccount);
}
