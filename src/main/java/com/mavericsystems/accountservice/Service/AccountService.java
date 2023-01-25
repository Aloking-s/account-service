package com.mavericsystems.accountservice.Service;

import com.mavericsystems.accountservice.Dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {



   public List<AccountDto> getAccounts(String customerId, Integer page, Integer pageSize);
    public AccountDto createAccount(AccountDto account);
    public AccountDto getAccountDetailsById(String customerId, String accountId);
    public AccountDto updateAccountDetails(String accountId,AccountDto accountDto);
    public String deleteAccount(String accountId);
    public List<AccountDto> getAccountsById(String customerId);


    /*public Accounts getAccountInfo(String acctID){
        return accountRepository.findById(acctID).orElse(null);
    }
    public void deleteAccount(String acctID){
        accountRepository.deleteById(acctID);
    }

    public long getBalance(String acctID){
        return accountRepository.findBalanceByAcctID(acctID);
    }
    public void depositAmount(String acctID , long amount){
        accountRepository.saveBalanceByAcctID(acctID, amount);
    }
    public void withdrawAmount(String acctID , long amount){
        accountRepository.withdrawAmountByAcctID(acctID , amount);
    }

    public void transferAmount(String acctID , String destAcctID ,long amount ){
        accountRepository.withdrawAmountByAcctID(acctID , amount);
        accountRepository.saveBalanceByAcctID(destAcctID , amount);
    }*/

}
