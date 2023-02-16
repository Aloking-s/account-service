package com.mavericsystems.accountservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavericsystems.accountservice.Account.Accounts;
import com.mavericsystems.accountservice.Dto.AccountDto;
import com.mavericsystems.accountservice.Dto.Balance;
import com.mavericsystems.accountservice.Enums.Currency;
import com.mavericsystems.accountservice.Enums.Type;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;

@SpringBootTest
public class AccountServiceApplicationTests {
	public static final String apiV1 = "/api/v1/customers/1234/accounts";
	public static final String invalidapiV1 = "/api/v1/customers/0000/accounts/0000";
	@Test
	void contextLoads() {
		assertTrue(true);
	}
		public static String asJsonString(final Object obj){
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		}

		public static Accounts getAccount()
		{
			return Accounts.builder().
					customerId("1234")
					.type(Type.SAVINGS).build();
		}

		public static AccountDto getAccountDto(){
			return AccountDto.builder()
					.customerId("1234")
					.type(Type.SAVINGS)
					.build();
		}

		public static Balance getBalance(){

			return Balance.builder()
					.accountId("1234")
					.amount("1000")
					.currency(Currency.INR)
					.build();
		}


}
