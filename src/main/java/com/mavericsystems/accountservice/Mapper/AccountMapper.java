package com.mavericsystems.accountservice.Mapper;

import org.mapstruct.Mapper;
import com.mavericsystems.accountservice.Account.Accounts;
import com.mavericsystems.accountservice.Dto.AccountDto;

import java.util.List;

@Mapper(componentModel = "AccountDetails")
public interface AccountMapper {

    Accounts map (AccountDto accountDto);

    AccountDto map(Accounts account);

    List<Accounts> mapToModel (List<AccountDto> accounts);

    List<AccountDto> mapToDto (List<Accounts> accounts);


    
}
