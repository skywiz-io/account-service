package com.poalim.openshift.account;

import java.math.BigDecimal;
import java.util.List;

import com.poalim.openshift.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by osher on 19/7/17.
 */

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountRepository accounts;

    public Account findAccountById(Integer accountId) {

        logger.debug("AccountService-findById: id={}", accountId);

        return accounts.findById(accountId).orElseThrow(() ->
                new ResourceNotFoundException("Acoount not found:" + accountId));
    }

    public List<Account> findAccountsByName(String name) {

        logger.debug("AccountService-findAccountsByName: name={}", name);
        List<Account> accountList = accounts.findByName(name);
        logger.debug("Found " + accountList.size() + " account(s).");

        return accountList;
    }

    public Integer createAccount(Account account) {
        logger.debug("AccountService-createAccount: account: {}", account.toString());

        Account newAccount = new Account();
        newAccount.setUserid(account.getUserid());

        this.updateAccountProperties(newAccount, account);

        return this.save(newAccount).getId();
    }

    public Integer updateAccount(Account account) {

        logger.debug("AccountService-updateAccount: account: {}", account.toString());
        Account toUpdate = this.findAccountById(account.getId());

        this.updateAccountProperties(toUpdate, account);

        return this.save(toUpdate).getId();
    }

    public Account deleteAccount(Account account) {

        logger.debug("AccountService-deleteAccount: account: {}", account.toString());
        Account toUpdate = this.findAccountById(account.getId());

        return this.delete(toUpdate);
    }

    public void addFunds(Integer accountId, BigDecimal amount) {
        logger.debug("AccountService-addFunds: account: {}, amount: {}", accountId, amount);
        this.findAccountById(accountId).addFunds(amount);
    }

    public void withdrawalFunds(Integer accountId, BigDecimal amount) {
        logger.debug("AccountService-addFunds: account: {}, amount: {}", accountId, amount);
        this.findAccountById(accountId).withdrawalFunds(amount);
    }

    private void updateAccountProperties(Account toUpdate, Account newData) {
        // Only update allowed properties
        toUpdate.setName(newData.getName());
    }

    private Account save(Account account) {
        return accounts.save(account);
    }

    private Account delete(Account account) {
        accounts.delete(account);
        return account;
    }
}
