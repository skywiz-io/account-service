package com.poalim.openshift.transaction;

import com.poalim.openshift.account.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by osher on 20/7/17.
 */

@Data
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private String id;

    @Column(name = "amount", precision = 14, scale = 2, nullable=false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account toAccount;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @PrePersist
    private void creationdate() {
        this.date = new Date();
    }
}
