package com.zeno.hsbc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTransaction {

    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "transaction ID")
    @NotNull(message = "交易ID不能为空")
    private String tid;

    @Schema(description = "交易类型")
    @NotNull(message = "交易类型不能为空")
    private String type;

    @Schema(description = "交易金额")
    @NotNull(message = "交易金额不能为空")
    private Double amount;

    @Schema(description = "货币类型")
    @NotNull(message = "货币类型不能为空")
    private String currency;

    @Schema(description = "交易时间，unix毫秒时间戳", example = "1743305743000")
    @NotNull(message = "交易时间不能为空")
    private Long transactionTime;

    @Schema(description = "目标用户ID")
    private Long targetUid;

    @Schema(description = "链接键")
    private String linkKey;
}
