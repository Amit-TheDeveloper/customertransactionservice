# Getting Started

### Reference Documentation

This project is a SpringBoot application which exposes REST endpoints to enable various operations to facilitate transactions on a Customer Account.
The project is organized as a maven project with pom.xml in root directory. You must build the resource using your IDE or maven from the command line prior to starting the applications to prevent startup issues.

You can build the project from the commandline by navigating to the root directory and invoking mvn -U clean install -DskipTests

1) Supported Api's: Header used: Content-Type: application/json

1.1) Send a POST request to register a new Customer. http://localhost:9091/customer/register
   Sample Payload:
   {
       "firstName": "Andrews",
       "lastName": "Oliva",
       "address": "123 Street-1 Paris Europe",
       "phoneNumber": "9988776655",
       "email": "andrews@gmail.com"
   }
1.2) POST a request to open a customer Account. Input is customerID(1) and Account Type(CURRENT/SAVING) 
http://localhost:9091/account/1/CURRENT/open >> Current Account.
http://localhost:9091/account/1/SAVING/open >> SAVING Account.

1.3) POST a transaction request to an Account. http://localhost:9091/account/transaction
    Sample Payload:
    {
        "amount": 30.2,
        "txnType": "CREDIT",
        "account": {
        "accountId": "1",
        "customer": {
        "customerId": "1"
    }
    }
        "phoneNumber": "123456789",
        "email": "amit@abc.com"
    }
1.4) Find Balance of an Account http://localhost:9091/account/1/balance
1.5) Send a GET request to find a Customer by ID. http://localhost:9091/customer/1
1.6) Send a GET request to find all Customers. http://localhost:9091/customer/all
1.7) Send a GET request to fetch an Account by Id. http://localhost:9091/account/1
1.8) Send a GET request to fetch all Accounts. http://localhost:9091/account/all
1.9) Send a GET request to fetch all transactions on an Account. http://localhost:9091/account/1/transactions
1.10) Send a GET request to find a transaction by Id. http://localhost:9091/transaction/1
1.11) Send a DELETE request to delete a Transaction by id. http://localhost:9091/transaction/1

1.12) Send a DELETE request to delete an Account by Id. http://localhost:9091/account/2
1.13) Send a DELETE request to delete a Customer by ID. http://localhost:9091/customer/4


1) How to start/run service:

1.1) Running Service from console:
Open terminal and do below steps.
1) cd <path to service>/customertransactionservice
2) mvn spring-boot:run

1.2) Running Service from IDE IntelliJ/Eclipse:
Go to file CustomerTransactionServiceApplicationTests and run as Java Application as this is entry point.

2) How to run/execute tests:
   2.1) From console: open another terminal and do below steps.
1) cd <path to service>/customertransactionservice
2) mvn test

2.2) From java file: Go to file CustomerTransactionServiceApplicationTests and run it.

3) Viewing results:

3.1) Viewing results from Console: Test results are recocrded on console. To view, check the logs in between texts
[======RESULTS=====RESULTS======] and [====RESULTS====END======RESULTS]

3.2) Viewing records from browser(H2-console):
As the application uses in-memory data base H2. Data can be viewd from H2 browser console.
1) Open browser and go to url [http://localhost:9091/h2-console/login.jsp]
2) Use JDBC URL: jdbc:h2:mem:customertransactiondb
3) Click connect.
Tables available are CUSTOMERS, ACCOUNTS and TRANSACTIONS for respective activities.

3.3) GET api's can be hit from browser to get the results.

4) Executing POST/DELETE requests: This can be done using Postman and providing simple payload. 