package com.mavericsystems.accountservice.Controller;

import com.mavericsystems.accountservice.Account.Accounts;
import com.mavericsystems.accountservice.Consumer.BalanceServiceConsumer;
import com.mavericsystems.accountservice.Consumer.TransactionServiceConsumer;
import com.mavericsystems.accountservice.Dto.AccountDto;
import com.mavericsystems.accountservice.Dto.Balance;
import com.mavericsystems.accountservice.Enums.Currency;
import com.mavericsystems.accountservice.Mapper.AccountMapper;
import com.mavericsystems.accountservice.Repository.AccountRepository;
import com.mavericsystems.accountservice.Service.AccountService;
import com.mavericsystems.accountservice.Controller.AccountController;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.mavericsystems.accountservice.AccountServiceApplicationTests.*;
import static com.mavericsystems.accountservice.constants.Constants.ACCOUNT_DELETED_SUCCESS;
import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.List;

@ContextConfiguration(classes = AccountController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
@Tag("Integration Test")
public class AccountControllerTest {

    @Mock
    ResponseEntity<List<Balance>> balance;

    @Mock
    private MockMvc mock;

    @Mock
    AccountRepository repository;

    @Mock
    private AccountService accountService;

    @Mock
    private AccountMapper mapper;

    @MockBean
    BalanceServiceConsumer balanceServiceConsumer;

    @MockBean
    TransactionServiceConsumer transactionServiceConsumer;

    @Test
    public void shouldGetStatus200WhenReqMadeToGetAccounts () throws Exception
    {
        mock.perform(get(apiV1)
                .contentType(MediaType.APPLICATION_JSON).header("userID" , "1234"))
        .andExpect(status().isOk())
            .andDo(print());
    }
    @Test
    public void shouldGetStatus400WhenRequestMadeTogetAccounts() throws Exception
    {

        mock.perform(get(invalidapiV1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToCreateAccounts() throws Exception
    {
        when(accountService.createAccount((AccountDto.class))).thenReturn(getAccountDto());
        mock.perform(post(apiV1)
                        .contentType(MediaType.APPLICATION_JSON).header("userId", "1234")
                        .content(asJsonString(getAccountDto()))
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus405WhenRequestMadeToCreateAccounts() throws Exception {
        when(accountService.createAccount(any( AccountDto.class))).thenReturn(getAccountDto());
        mock.perform(post(invalidapiV1)
                        .contentType(MediaType.APPLICATION_JSON).header("userId", "1234")
                        .content(asJsonString(getAccountDto()))
                )
                .andExpect(status().isMethodNotAllowed())
                .andDo(print());

    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToGetAccountDetails() throws Exception
    {
        when(accountService.getAccountDetailsById(any(String.class))).thenReturn(getAccountDto());
        when(balanceServiceConsumer.getBalanceAccountDetails(any(),any())).thenReturn(getSampleBalance());
        mock.perform(get(apiV1+"/accountId1").header("userId","1234"))
                .andExpect(status().isOk())
                .andReturn();
    }
    public ResponseEntity<Balance> getSampleBalance(){

        Balance balance = new Balance();
        balance.setCurrency(Currency.INR);
        balance.setAccountId("4");
        balance.setAmount("200");
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToUpdateAccount() throws Exception
    {
        mock.perform(put(apiV1+"/1234")
                        .contentType(MediaType.APPLICATION_JSON).header("userId", "1234")
                        .content(asJsonString(getAccountDto()))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus404WhenRequestMadeToUpdateAccount() throws Exception
    {
        mock.perform(put(apiV1+"/1234")
                        .contentType(MediaType.APPLICATION_JSON).header("userId", "3456")
                        .content(asJsonString(getAccountDto()))
                )
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus404WhenRequestMadeToDeleteAccount() throws Exception
    {
        mock.perform(delete(apiV1+"/1234").header("userId","1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToDeleteAccount() throws Exception
    {
        when(accountService.deleteAccount(any(String.class))).thenReturn(ACCOUNT_DELETED_SUCCESS);
        when(balanceServiceConsumer.deleteBalanceByAccountId(any(String.class))).thenReturn(null);
        when(transactionServiceConsumer.deleteAllTransaction(any(String.class))).thenReturn(null);
        mock.perform(delete(apiV1+"/1234").header("userId","1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
