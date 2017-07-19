package com.poalim.openshift.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import com.poalim.openshift.account.domain.Account;
import com.poalim.openshift.account.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by osher on 19/7/17.
 */

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService service;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> findById(@PathVariable("id") final Integer id) {

        logger.info("AccountController-findById: {}", id);
        return new ResponseEntity<>(this.service.findAccountById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/name={name}", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> findByName(@PathVariable("name") String name) {

        logger.info("AccountController-findByName: {}", name);
        List<Account> accountsFound = this.service.findAccountsByName(name);
        return new ResponseEntity<>((accountsFound == null ? new ArrayList<Account>() : accountsFound), HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> createAccount(@RequestBody Account account,
                                       UriComponentsBuilder builder) {

        logger.info("AccountController-createAccount: {}", account);

        Integer accountId = this.service.createAccount(account);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(builder.path("/account/{id}").buildAndExpand(accountId).toUri());
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAccount(@RequestBody Account account,
                                                UriComponentsBuilder builder) {

        logger.info("AccountController-updateAccount: {}", account);

        Integer accountId = this.service.updateAccount(account);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(builder.path("/account/{id}").buildAndExpand(accountId).toUri());
        return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Account> deleteAccount(@RequestBody Account account) {

        logger.info("AccountController-deleteAccount: {}", account);

        Account deletedAccount = this.service.deleteAccount(account);
        return new ResponseEntity<>(deletedAccount, HttpStatus.OK);
    }
}
