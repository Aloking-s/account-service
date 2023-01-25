package com.mavericsystems.accountservice.Repository;

import com.mavericsystems.accountservice.Account.Accounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepository extends CrudRepository<Accounts , String> {

   /* @Query("select balance from Accounts where acctID =?1")
    public long findBalanceByAcctID (String acctID);

    @Transactional
    @Query("update Accounts set balance = ?2 where acctID = ?1")
    public void saveBalanceByAcctID(String acctID , long balance);

    @Transactional
    @Query("update Accounts set balance = ?2 where acctID = ?1")
    public void withdrawAmountByAcctID(String acctID , long balance);*/

    List<Accounts> findByCustomerId (String CustomerId);
    @Query("{customerId : ? 0}")
    Page<Accounts> findAllByCustomerId (Pageable pageable , String CustomerId);

    @Query("{'customerId' : ? 0 , 'acctId' : ? 1}")
    Accounts findAccountByCustomerId(String customerId , String acctId);

}
