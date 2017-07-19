package com.poalim.openshift.account.domain;


import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by osher on 19/7/17.
 */
@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "userid", nullable = false, length = 250)
    private String userid;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "creationdate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date creationdate;

    @Column(name = "balance", precision = 14, scale = 2, nullable=false)
    private BigDecimal balance = new BigDecimal(0);
}
