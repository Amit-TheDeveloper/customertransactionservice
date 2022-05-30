package com.enterprise.payments.application.business.model;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CustomerTransactionServiceApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTransactionServiceApplicationTests.class);
	static Map<String, List<String>> resultMap = new LinkedHashMap<>();


//	@BeforeEach
//	void pause() {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	// Customer Service Tests

	/**
	 * Add 3 new Customers
	 */
	@Test
	@Order(value = 1)
	void registerCustomer() {
		resultMap.put("REGISTER CUSTOMER", new ArrayList<>());
		String custRegister = "http://localhost:9091/customer/register";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");

		String jsonBody1 = "{\"firstName\": \"Amit\",\"lastName\": \"Chandra\",\"address\": \"123 Street-1 Paris Europe\",\"phoneNumber\": \"9898989898\",\"email\": \"amit@gmail.com\"}";
		String jsonBody2 = "{\"firstName\": \"Shilpi\",\"lastName\": \"Chandra\",\"address\": \"456 Street-2 Rome Europe\",\"phoneNumber\": \"4356837621\",\"email\": \"shilpi@gmail.com\"}";
		String jsonBody3 = "{\"firstName\": \"Aashvi\",\"lastName\": \"Chandra\",\"address\": \"456 Street-3 Berlin Europe\",\"phoneNumber\": \"7196879988\",\"email\": \"aashvi@gmail.com\"}";

		String jsonBody4 = "{\"firstName\": \"Andrews\",\"lastName\": \"Charlotte\",\"address\": \"789 Street-4 London UK\",\"phoneNumber\": \"9800778800\",\"email\": \"andrews@gmail.com\"}";
		//String jsonBody5 = "{\"firstName\": \"Oliva\",\"lastName\": \"Ameli\",\"address\": \"999 Street-5 Rome Europe\",\"phoneNumber\": \"6669903321\",\"email\": \"oliva@gmail.com\"}";
		//String jsonBody6 = "{\"firstName\": \"Noah\",\"lastName\": \"Emma\",\"address\": \"777 Street-6 Madrid Europe\",\"phoneNumber\": \"6565330099\",\"email\": \"noah@gmail.com\"}";

		HttpEntity<String> entity = new HttpEntity<String>(jsonBody1, headers);
		HttpEntity<String> entity2 = new HttpEntity<String>(jsonBody2, headers);
		HttpEntity<String> entity3 = new HttpEntity<String>(jsonBody3, headers);
		HttpEntity<String> entity4 = new HttpEntity<String>(jsonBody4, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(custRegister, entity, String.class);
		ResponseEntity<String> response2 = restTemplate.postForEntity(custRegister, entity2, String.class);
		ResponseEntity<String> response3 = restTemplate.postForEntity(custRegister, entity3, String.class);
		ResponseEntity<String> response4 = restTemplate.postForEntity(custRegister, entity4, String.class);

		resultMap.get("REGISTER CUSTOMER").add(response.getBody());
		resultMap.get("REGISTER CUSTOMER").add(response2.getBody());
		resultMap.get("REGISTER CUSTOMER").add(response3.getBody());
		resultMap.get("REGISTER CUSTOMER").add(response4.getBody());

		org.junit.jupiter.api.Assertions.assertSame(response.getStatusCode(), HttpStatus.CREATED);
		org.junit.jupiter.api.Assertions.assertSame(response2.getStatusCode(), HttpStatus.CREATED);
		org.junit.jupiter.api.Assertions.assertSame(response3.getStatusCode(), HttpStatus.CREATED);
		org.junit.jupiter.api.Assertions.assertSame(response4.getStatusCode(), HttpStatus.CREATED);

	}


	/**
	 * Find Customer by ID 1
	 */
	@Test
	@Order(value = 2)
	void findCustomerId1() {
		resultMap.put("FIND CUSTOMER BY ID 1", new ArrayList<>());
		String custRegister = "http://localhost:9091/customer/1";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(custRegister, String.class);
		resultMap.get("FIND CUSTOMER BY ID 1").add(response.getBody());
		org.junit.jupiter.api.Assertions.assertSame(response.getStatusCode(), HttpStatus.OK);
	}

	/**
	 * Find All Customers. Note Customer ID 2 is deleted
	 */
	@Test
	@Order(value = 3)
	void findAllCustomer() {
		resultMap.put("FIND ALL CUSTOMERS", new ArrayList<>());
		String allCustomer = "http://localhost:9091/customer/all";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(allCustomer, String.class);
		resultMap.get("FIND ALL CUSTOMERS").add(response.getBody());
		org.junit.jupiter.api.Assertions.assertSame(response.getStatusCode(), HttpStatus.OK);

	}


	//Account Service Test cases

	/**
	 * Open Account for Customer Id 1
	 */
	@Test
	@Order(value = 4)
	void openCustomerAccount() {
		resultMap.put("OPEN CUSTOMER ACCOUNT", new ArrayList<>());
		String openCustomerAccount1 = "http://localhost:9091/account/1/SAVING/open";
		String openCustomerAccount2 = "http://localhost:9091/account/2/CURRENT/open";
		String openCustomerAccount3 = "http://localhost:9091/account/3/CURRENT/open";
		String openCustomerAccount4 = "http://localhost:9091/account/4/CURRENT/open";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response1 = restTemplate.postForEntity(openCustomerAccount1, "", String.class);
		ResponseEntity<String> response2 = restTemplate.postForEntity(openCustomerAccount2, "", String.class);
		ResponseEntity<String> response3 = restTemplate.postForEntity(openCustomerAccount3, "", String.class);
		ResponseEntity<String> response4 = restTemplate.postForEntity(openCustomerAccount4, "", String.class);

		resultMap.get("OPEN CUSTOMER ACCOUNT").add(response1.getBody());
		resultMap.get("OPEN CUSTOMER ACCOUNT").add(response2.getBody());
		resultMap.get("OPEN CUSTOMER ACCOUNT").add(response3.getBody());
		resultMap.get("OPEN CUSTOMER ACCOUNT").add(response4.getBody());
		org.junit.jupiter.api.Assertions.assertSame(response1.getStatusCode(), HttpStatus.CREATED);
		org.junit.jupiter.api.Assertions.assertSame(response2.getStatusCode(), HttpStatus.CREATED);
		org.junit.jupiter.api.Assertions.assertSame(response3.getStatusCode(), HttpStatus.CREATED);
		org.junit.jupiter.api.Assertions.assertSame(response4.getStatusCode(), HttpStatus.CREATED);

	}

	/**
	 * Do a transaction
	 */
	@Test
	@Order(value = 5)
	void doTransaction() {
		resultMap.put("DO TRANSACTION RESULT", new ArrayList<>());
		String doTransactionUrl = "http://localhost:9091/account/transaction";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");
		RestTemplate restTemplate = new RestTemplate();

		String txn1 = "{\"amount\": 30.20,\"txnType\": \"CREDIT\",\"account\": {\"accountId\": \"1\",\"customer\": {\"customerId\": \"1\"}},\"phoneNumber\": \"123456789\",\"email\": \"amit@abc.com\"}";
		HttpEntity<String> entity1 = new HttpEntity<String>(txn1, headers);
		ResponseEntity<String> response1 = restTemplate.postForEntity(doTransactionUrl, entity1, String.class);

		String txn2 = "{\"amount\": 40.30,\"txnType\": \"CREDIT\",\"account\": {\"accountId\": \"1\",\"customer\": {\"customerId\": \"1\"}},\"phoneNumber\": \"123456789\",\"email\": \"amit@abc.com\"}";
		HttpEntity<String> entity2 = new HttpEntity<String>(txn2, headers);
		ResponseEntity<String> response2 = restTemplate.postForEntity(doTransactionUrl, entity2, String.class);

		String txn3 = "{\"amount\": 30.20,\"txnType\": \"DEBIT\",\"account\": {\"accountId\": \"1\",\"customer\": {\"customerId\": \"1\"}},\"phoneNumber\": \"123456789\",\"email\": \"amit@abc.com\"}";
		HttpEntity<String> entity3 = new HttpEntity<String>(txn3, headers);
		ResponseEntity<String> response3 = restTemplate.postForEntity(doTransactionUrl, entity3, String.class);

		String txn4 = "{\"amount\": 30.20,\"txnType\": \"CREDIT\",\"account\": {\"accountId\": \"1\",\"customer\": {\"customerId\": \"1\"}},\"phoneNumber\": \"123456789\",\"email\": \"amit@abc.com\"}";
		HttpEntity<String> entity4 = new HttpEntity<String>(txn4, headers);
		ResponseEntity<String> response4 = restTemplate.postForEntity(doTransactionUrl, entity4, String.class);

		resultMap.get("DO TRANSACTION RESULT").add(response1.getBody());
		resultMap.get("DO TRANSACTION RESULT").add(response2.getBody());
		resultMap.get("DO TRANSACTION RESULT").add(response3.getBody());
		resultMap.get("DO TRANSACTION RESULT").add(response3.getBody());

		org.junit.jupiter.api.Assertions.assertSame(response1.getStatusCode(), HttpStatus.OK);
		org.junit.jupiter.api.Assertions.assertSame(response2.getStatusCode(), HttpStatus.OK);
		org.junit.jupiter.api.Assertions.assertSame(response3.getStatusCode(), HttpStatus.OK);
		org.junit.jupiter.api.Assertions.assertSame(response4.getStatusCode(), HttpStatus.OK);

	}

	/**
	 * Find Account by ID 1
	 */
	@Test
	@Order(value = 6)
	void findAccountByID() {
		resultMap.put("FIND ACCOUNT BY ID", new ArrayList<>());
		String accountUrl = "http://localhost:9091/account/1";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(accountUrl, String.class);
		resultMap.get("FIND ACCOUNT BY ID").add(response.getBody());
		org.junit.jupiter.api.Assertions.assertSame(response.getStatusCode(), HttpStatus.OK);

	}

	/**
	 * Find All accounts
	 */
	@Test
	@Order(value = 7)
	void findAllAccounts() {
		resultMap.put("FIND ALL ACCOUNTS", new ArrayList<>());
		String allAccountUrl = "http://localhost:9091/account/all";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(allAccountUrl, String.class);
		resultMap.get("FIND ALL ACCOUNTS").add(response.getBody());
		org.junit.jupiter.api.Assertions.assertSame(response.getStatusCode(), HttpStatus.OK);

	}

	/**
	 * Find Transactions on Account with ID 1
	 */
	@Test
	@Order(value = 8)
	void findAccountTransactions() {
		resultMap.put("ALL TRANSACTIONS OF ACCOUNT ID 1", new ArrayList<>());
		String accountTransactionsUrl = "http://localhost:9091/account/1/transactions";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(accountTransactionsUrl, String.class);
		resultMap.get("ALL TRANSACTIONS OF ACCOUNT ID 1").add(response.getBody());
		org.junit.jupiter.api.Assertions.assertSame(response.getStatusCode(), HttpStatus.OK);

	}

	// Transactions Service Tests

	/**
	 * Find Transaction with ID 1
	 */
	@Test
	@Order(value = 9)
	void findTransactionsByID() {
		resultMap.put("TRANSACTION WITH ID 1", new ArrayList<>());
		String transactionsUrl = "http://localhost:9091/transaction/1";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(transactionsUrl, String.class);
		resultMap.get("TRANSACTION WITH ID 1").add(response.getBody());
		org.junit.jupiter.api.Assertions.assertSame(response.getStatusCode(), HttpStatus.OK);

	}


	/**
	 * Account Balance for Account ID 1
	 */
	@Test
	@Order(value = 10)
	void findAccountBalance() {
		resultMap.put("Balance For Account ID 1", new ArrayList<>());
		String transactionsUrl = "http://localhost:9091/account/1/balance";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(transactionsUrl, String.class);
		resultMap.get("Balance For Account ID 1").add(response.getBody());
		org.junit.jupiter.api.Assertions.assertSame(response.getStatusCode(), HttpStatus.OK);

	}

//  Want to test delete api's, uncomment below functions.
//  Below test will fail in subsequent run without server restart,
//  as Id are no longer present.

	/**
	 * Delete transaction with IS 2
	 *
	 * Note: If rerun, will fail if server is not restarted
	 */

//	@Test
//	@Order(value = 11)
//	void deleteTransactionById() {
//		resultMap.put("DELETE TRANSACTION WITH ID 1", new ArrayList<>());
//		String deleteTransaction = "http://localhost:9091/transaction/1";
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.delete(deleteTransaction);
//		resultMap.get("DELETE TRANSACTION WITH ID 1").add("Deleted Transaction id 1");
//	}

	/**
	 * Delete Account with ID 2
	 * If rerun, will fail if server is not restarted
	 */
//	@Test
//	@Order(value = 12)
//	void deleteAccountId2() {
//		resultMap.put("DELETE ACCOUNT WITH ID 2", new ArrayList<>());
//		String deleteAccount = "http://localhost:9091/account/2";
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.delete(deleteAccount);
//		resultMap.get("DELETE ACCOUNT WITH ID 2").add("Deleted Account id 2");
//
//	}

	/**
	 * Delete Customer with ID 4
	 */
//	@Test
//	@Order(value = 13)
//	void deleteCustomerId4() {
//		resultMap.put("DELETE CUSTOMER BY ID 4", new ArrayList<>());
//		String deleteCustomer = "http://localhost:9091/customer/4";
//		HttpHeaders headers = new HttpHeaders();
//
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.delete(deleteCustomer);
//		resultMap.get("DELETE CUSTOMER BY ID 4").add("Deleted Customer id 4");
//
//	}


	/**
	 * Print Results
	 */
	@AfterAll
	static void printResults() {
		LOGGER.info("Printing test execution Results...");
		Set<String> keys = resultMap.keySet();
		System.out.println("==========RESULTS==========RESULTS===========RESULTS=========RESULTS=======");
		keys.forEach(s -> {
			System.out.println(">> " + s);
			resultMap.get(s).forEach(System.out::println);
			System.out.println("");
			System.out.println("");
		});
		System.out.println("==========RESULTS====END======RESULTS====END=======RESULTS====END=====RESULTS====END===");

	}


}
