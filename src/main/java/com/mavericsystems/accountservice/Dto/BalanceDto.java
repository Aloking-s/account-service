package com.mavericsystems.accountservice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class BalanceDto {

    private String  customerId;
    private String accountId;
    private Number amount;
    private String currency;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
