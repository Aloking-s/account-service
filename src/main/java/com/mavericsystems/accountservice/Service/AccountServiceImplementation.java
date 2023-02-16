package com.mavericsystems.accountservice.Service;

import com.mavericsystems.accountservice.Account.Accounts;
import com.mavericsystems.accountservice.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.mavericsystems.accountservice.Mapper.AccountMapper;
import com.mavericsystems.accountservice.Dto.AccountDto;
import com.mavericsystems.accountservice.exception.AccountNotFoundException;
import com.mavericsystems.accountservice.Enums.Type;

import java.util.ArrayList;
import java.util.List;

import static com.mavericsystems.accountservice.constants.Constants.*;

@Service
public class AccountServiceImplementation implements AccountService{

    @Autowired
    AccountRepository repository;

    @Autowired
    private AccountMapper mapper;

    @Override
    public List<AccountDto> getAccounts (String customerId, Integer page, Integer pageSize) {
        Page<Accounts> pageResult = repository.findAllByCustomerId(PageRequest.of(page, pageSize),customerId);
        if(pageResult.hasContent()) {
            List<Accounts> account = pageResult.getContent();
            return mapper.mapToDto(account);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        accountDto.setCreatedAt(getCurrentDateTime());
        accountDto.setUpdatedAt(getCurrentDateTime());

        Accounts account = mapper.map(accountDto);
        Accounts accountResult = repository.save(account);
        return  mapper.map(accountResult);
    }

    @Override
    public AccountDto getAccountDetailsById(String customerId, String accountId) {
        Accounts accountResult=repository.findAccountByCustomerId(customerId,accountId);
        if(accountResult==null){
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE+accountId);
        }
        return mapper.map(accountResult);
    }
    @Override
    public AccountDto updateAccountDetails(String accountId, AccountDto accountDto) {
        Accounts accountResult=repository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE+accountId));
        accountResult.setAcctId(accountResult.getAcctId());
        accountResult.setCustomerId(accountDto.getCustomerId());
        accountResult.setType(accountDto.getType());
        accountResult.setCreatedAt(accountResult.getCreatedAt());
        accountResult.setUpdatedAt(getCurrentDateTime());
        Accounts accountUpdated = repository.save(accountResult);
        return mapper.map(accountUpdated);
    }

    @Override
    public String deleteAccount(String accountId) {
        if(!repository.findById(accountId).isPresent())
        {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE+accountId);
        }
        repository.deleteById(accountId);
        return ACCOUNT_DELETED_SUCCESS;
    }

    @Override
    public List<AccountDto> getAccountsById(String customerId) {
        List<Accounts> account=repository.findByCustomerId(customerId);
        return mapper.mapToDto(account);
    }

}
