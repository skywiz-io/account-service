package com.poalim.openshift.transaction;

import com.poalim.openshift.account.Account;
import com.poalim.openshift.account.AccountService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by osher on 20/7/17.
 */

@RestController
@RequestMapping("/accounts")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    private ModelMapper modelMapper = new ModelMapper(); // Make it a bean and autowire

    @RequestMapping(value = "/transactions/{transactionId}", method = RequestMethod.GET)
    public ResponseEntity<TransactionDTO> findById(@PathVariable("transactionId") final String transactionId) {

        logger.info("TransactionController-findById: {}", transactionId);
        return new ResponseEntity<>(convertToDto(this.transactionService.findById(transactionId)),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountId}/transactions", method = RequestMethod.GET)
    public ResponseEntity<List<TransactionDTO>> findByAccount(
            @PathVariable("accountId") final Integer accountId) {

        logger.info("TransactionController-findByAccount: accountId: {}", accountId);
        Account account = this.accountService.findAccountById(accountId);
        List<Transaction> transactions = this.transactionService.findByToAccountOrFromAccount(account, account);
        List<TransactionDTO> transactionsDTO = transactions.stream().
                map(transaction -> convertToDto(transaction)).collect(Collectors.toList());
        return new ResponseEntity<>(transactionsDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountId}/transactions", method = RequestMethod.POST)
    public ResponseEntity<List<TransactionDTO>> makeTransaction(
            @PathVariable("accountId") final Integer accountId,
            @RequestBody final TransactionDTO transactionDTO,
            UriComponentsBuilder builder) {

        logger.info("TransactionController-makeTransaction: accountId: {}, transaction: {}",
                accountId, transactionDTO.toString());
        String transactionId = this.transactionService.makeTransacition(accountId,
                transactionDTO.getToAccountId(), transactionDTO.getAmount(),
                transactionDTO.getDescription());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(builder.path("/transactions/{id}").
                buildAndExpand(transactionId).toUri());
        return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
    }

    private TransactionDTO convertToDto(Transaction transaction) {
        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
        return transactionDTO;
    }

    private Transaction convertToEntity(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        transaction.setFromAccount(accountService.
                findAccountById(transactionDTO.getFromAccountId()));
        transaction.setToAccount(accountService.
                findAccountById(transactionDTO.getToAccountId()));

        return transaction;
    }

}
