package com.zeno.hsbc.vo;


import com.zeno.hsbc.entity.Transaction;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionVo {
    private Long id;

    @Schema(description = "用户ID")
    @NotNull
    private Long userId;

    @Schema(description = "transaction ID")
    @NotNull
    private String tid;

    @Schema(description = "交易类型")
    @NotNull
    private String type;

    @Schema(description = "交易金额")
    @NotNull
    private Double amount;

    @Schema(description = "货币类型")
    @NotNull
    private String currency;

    @Schema(description = "交易时间，unix毫秒时间戳", example = "1743305743000")
    @NotNull
    private Long transactionTime;

    @Schema(description = "目标用户ID")
    private Long targetUid;

    @Schema(description = "链接键")
    private String linkKey;

    public TransactionVo(Transaction transaction) {
        this.id = transaction.getId();
        this.amount  = transaction.getAmount();
        this.currency = transaction.getCurrency();
        this.tid = transaction.getTid();
        this.type = transaction.getType();
        this.transactionTime = transaction.getTransactionTime();
        this.userId = transaction.getUserId();
        this.targetUid = transaction.getTargetUid();
        this.linkKey = transaction.getLinkKey();
    }

    public Transaction toEntity() {
        Transaction transaction = new Transaction();
        transaction.setId(this.id);
        transaction.setTid(this.tid);
        transaction.setUserId(this.userId);
        transaction.setType(this.type);
        transaction.setAmount(this.amount);
        transaction.setCurrency(this.currency);
        transaction.setTransactionTime(this.transactionTime);
        transaction.setTargetUid(this.targetUid);
        transaction.setLinkKey(this.linkKey);
        return transaction;
    }
}
