package com.poalim.openshift.transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by osher on 20/7/17.
 */

@Data
public class TransactionDTO {

    private String id;
    private BigDecimal amount;
    private Integer fromAccountId;
    private Integer toAccountId;
    private Date date;
    private String description;
}
