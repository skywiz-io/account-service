package com.poalim.openshift.transaction;

import com.poalim.openshift.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by osher on 20/7/17.
 */
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Optional<Transaction> findById(String id);
    Optional<List<Transaction>> findByToAccountOrFromAccount(Account fromAccount, Account toAccount);
}
