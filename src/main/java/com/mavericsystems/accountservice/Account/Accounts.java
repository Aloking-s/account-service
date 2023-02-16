package com.mavericsystems.accountservice.Account;

import com.mavericsystems.accountservice.Enums.Type;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account-service")
public class Accounts {
@Id
   private String acctId;

    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;

    @NotNull(message = "Type is mandatory - 'SAVINGS' or 'CURRENT'")
    private Type type;

    private String Balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


   /* public Accounts(){

    }*/
    /*public Accounts (String customerId, String type){
        super();
        this.customerId = customerId;
*/
    /*public String getCustomerId(){
        return customerId;
    }
    public String getAcctID(){
        return acctID;
    }
    public void setAcctID(String acctID) {
        this.acctID = acctID;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(String acctStatus) {
        this.acctStatus = acctStatus;
    }*/
}
