package com.mavericsystems.accountservice.Dto;


import lombok.*;
import com.mavericsystems.accountservice.Enums.Currency;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    private String customerId;
    private String amount;
    private Currency currency;

    @NotBlank(message = "AccountId is mandatory")
    private String accountId;
}