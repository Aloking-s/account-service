package com.mavericsystems.accountservice.Consumer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface TransactionServiceConsumer {

    @DeleteMapping("accounts/{accountId}/transactions")
    public String deleteAllTransaction(@PathVariable String accountId);
}
