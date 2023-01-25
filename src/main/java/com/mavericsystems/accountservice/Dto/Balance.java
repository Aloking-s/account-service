package com.mavericsystems.accountservice.Dto;


import lombok.*;
import com.mavericsystems.accountservice.Enums.Currency;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    private String customerId;
    private String amount;
    private Currency currency;
    private String accountId;
}