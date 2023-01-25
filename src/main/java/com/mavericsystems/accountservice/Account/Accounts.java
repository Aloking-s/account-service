package com.mavericsystems.accountservice.Account;

import com.mavericsystems.accountservice.Enums.Type;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String customerId;
    private Type type;
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
