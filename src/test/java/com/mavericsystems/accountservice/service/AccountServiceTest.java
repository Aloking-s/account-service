package com.mavericsystems.accountservice.service;


import com.mavericsystems.accountservice.Account.Accounts;
import com.mavericsystems.accountservice.Dto.AccountDto;
import com.mavericsystems.accountservice.Mapper.AccountMapperImpl;
import com.mavericsystems.accountservice.Repository.AccountRepository;
import com.mavericsystems.accountservice.Service.AccountServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static com.mavericsystems.accountservice.AccountServiceApplicationTests.getAccount;
import static com.mavericsystems.accountservice.AccountServiceApplicationTests.getAccountDto;
import static javax.swing.text.html.HTML.Tag.S;
import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertSame;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImplementation service;

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapperImpl mapper;

    @Mock
    private Page pageResult;

    @Test
    public void testCreateAccount() throws Exception{
        //when(mapper.map(any(AccountDto.class))).thenReturn(getAccount());
        when(mapper.map(any(Accounts.class))).thenReturn(getAccountDto());
        when(repository.save(any())).thenReturn(getAccount());
        AccountDto accountDto= service.createAccount(getAccountDto());
        assertSame(accountDto.getCustomerId(), getAccountDto().getCustomerId());
    }

}
