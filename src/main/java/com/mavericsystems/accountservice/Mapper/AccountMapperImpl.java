package com.mavericsystems.accountservice.Mapper;

import com.mavericsystems.accountservice.Account.Accounts;
import com.mavericsystems.accountservice.Dto.AccountDto;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Accounts map(AccountDto accountDto)
    {
        return Accounts.builder().
                acctId(accountDto.getAcctId()).
                customerId(accountDto.getCustomerId()).
                type(accountDto.getType()).
                createdAt(accountDto.getCreatedAt()).
                updatedAt(accountDto.getUpdatedAt()).
                build();
    }
    @Override
    public AccountDto map(Accounts accounts)
    {
        return AccountDto.builder().
                acctId(accounts.getAcctId()).
                customerId(accounts.getCustomerId()).
                type(accounts.getType()).
                createdAt(accounts.getCreatedAt()).
                updatedAt(accounts.getUpdatedAt()).
                build();
    }

    @Override
    public List<Accounts> mapToModel (List <AccountDto> accounts){
        return accounts.stream().map(accountDto -> Accounts.builder().
                acctId(accountDto.getAcctId()).
                customerId(accountDto.getCustomerId()).
                type(accountDto.getType()).
                createdAt(accountDto.getCreatedAt()).
                updatedAt(accountDto.getUpdatedAt()).
                        build()).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto>  mapToDto (List<Accounts> accounts){
        return accounts.stream().map(account -> AccountDto.builder().
                acctId(account.getAcctId()).
                customerId(account.getCustomerId()).
                type(account.getType()).
                createdAt(account.getCreatedAt()).
                updatedAt(account.getUpdatedAt()).
                build()).collect(Collectors.toList());
    }
}
