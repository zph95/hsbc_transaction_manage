package com.zeno.hsbc.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@Entity
@Table(name = "transaction", indexes = {
        @Index(name = "idx_user_id", columnList = "userId"),
        @Index(name = "idx_type", columnList = "type"),
        @Index(name = "idx_currency", columnList = "currency"),
        @Index(name = "idx_transaction_time", columnList = "transactionTime")
})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String tid;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currency;

    @Column(name = "transaction_time", nullable = false)
    private Long transactionTime;

    private Long targetUid;

    private String linkKey;

    @Column(name = "created_time", updatable = false)
    private LocalDateTime createdTime;

    @Column(name = "modified_time", updatable = false)
    private LocalDateTime modifiedTime;
}