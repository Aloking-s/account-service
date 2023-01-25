package com.mavericsystems.accountservice.Controller;


import com.mavericsystems.accountservice.Enums.Currency;
import com.mavericsystems.accountservice.Dto.AccountDto;
import com.mavericsystems.accountservice.Service.AccountService;
import com.mavericsystems.accountservice.exception.CustomerIdMissmatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import com.mavericsystems.accountservice.Dto.Balance;
import static com.mavericsystems.accountservice.constants.Constants.CUSTOMER_ID_ERROR;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //New Account Creation
    @PostMapping("customer/{customerId}/accounts")
    public ResponseEntity<AccountDto> createAccount(@PathVariable String customerId, @Valid @RequestBody AccountDto accountDto,
                                                    @RequestHeader(value = "userId") String userId) {
        if (customerId.equals(accountDto.getCustomerId()) && customerId.equals(userId)) {
            AccountDto accountDtoResponse = accountService.createAccount(accountDto);
            Balance balance = new Balance();
            balance.setAmount("0");
            balance.setCurrency(Currency.INR);
            balance.setAccountId(accountDtoResponse.getAcctId());
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);

        } else {
            throw new CustomerIdMissmatch(CUSTOMER_ID_ERROR);
        }
    }
    //To Get Accounts details from customer ID.
    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<List<AccountDto>> getAccountsbyId(@PathVariable String customerId){
        List<AccountDto> accountDtoResponse = accountService.getAccountsById(customerId);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }
    //To create new account if customer Id is not present . If present then provide the list.
    @GetMapping("customer/{customerId}/accounts")
    public ResponseEntity<List <AccountDto>>  getAccounts (@PathVariable String customerId,
                                                           @RequestParam(defaultValue = "0") Integer Page, @RequestHeader(value = "userId") String userId,
                                                           @RequestParam(value = "10") Integer pageSize){
        if(customerId.equals(userId)){
            List<AccountDto> accountDtoResponse = accountService.getAccounts(customerId, Page, pageSize);
            return new ResponseEntity<>(accountDtoResponse ,HttpStatus.OK);
        }else{
            throw new CustomerIdMissmatch(CUSTOMER_ID_ERROR);
        }
    }
    //Get the Account details from customer Id and account Id
    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable String customerId , @PathVariable String accountId , @RequestHeader(value = "userId") String userId){
        if(customerId.equals(userId)){
            AccountDto accountDtoResponse = accountService.getAccountDetailsById(customerId , accountId);
           // ResponseEntity<Balance> balanceDto = balanceServiceConsumer.getBalanceAccountDetails(accountId , userId);
            //accountDtoResponse.setBalance(balanceDto.getBody());
            return new ResponseEntity<>(accountDtoResponse , HttpStatus.OK);
        }else{
            throw new CustomerIdMissmatch(CUSTOMER_ID_ERROR);
        }
    }
    //To update account details
    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> updateAccount (@PathVariable String customerId, @PathVariable String accountId , @RequestBody AccountDto accountDto , @RequestHeader(value = "userId") String userId){
        if(customerId.equals(accountDto.getCustomerId()) && customerId.equals(userId)){
            AccountDto accountDtoResponse = accountService.updateAccountDetails(accountId , accountDto);
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }
    }

    //Delete the account
    @DeleteMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String customerId,
                                                @PathVariable String accountId) {
        //transactionServiceConsumer.deleteAllTransaction(accountId);
        String result = accountService.deleteAccount(accountId);
        if(result!=null) {
           // balanceServiceConsumer.deleteBalanceByAccountId(accountId);
            //transactionServiceConsumer.deleteAllTransaction(accountId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(accountId+"AccountIdNotFound", HttpStatus.NOT_FOUND);
        }
    }

}