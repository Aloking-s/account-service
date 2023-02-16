package com.mavericsystems.accountservice.repository;


import com.mavericsystems.accountservice.Repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mavericsystems.accountservice.Account.Accounts;

import java.util.List;

import static com.mavericsystems.accountservice.AccountServiceApplicationTests.getAccount;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@RunWith(SpringRunner.class)
public class AccountRepositoryTest {

    @Autowired
    AccountRepository repository;

    @Test
    public void testSave(){
        Accounts account = repository.save(getAccount());
        assertEquals("1234" ,account.getCustomerId());
    }

    @Test
    public void testFindAll() {
        List <Accounts> account = (List<Accounts>) repository.findAll();
        assertNotNull(account);
        assert(account.size()>0);
    }
}
