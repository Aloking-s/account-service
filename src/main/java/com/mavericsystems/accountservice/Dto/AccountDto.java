package com.mavericsystems.accountservice.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mavericsystems.accountservice.Enums.Type;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.jetbrains.annotations.NotNull;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountDto {

    @Id
    private String acctId;
    private Type type;
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Balance balance;


}
