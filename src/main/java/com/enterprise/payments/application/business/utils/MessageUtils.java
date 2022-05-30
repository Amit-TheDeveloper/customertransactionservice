package com.enterprise.payments.application.business.utils;

/**
 * Message Utility, for holding common messages.
 *
 * @author amit chandra
 */
public interface MessageUtils {

    String NO_ACCOUNT_FOUND = "Ooops.. No Accounts found";
    String ACCOUNT_BALANCE_NOT_FOUND = "Could not find your account balance, please try again";
    String ACCOUNT_BALANCE = "Woohoo... you have $ ";
    String NO_TRANSACTION_FOUND = "No Transactions found";
    String ACCOUNT_NOT_DELETED = "Error deleting Account";
    String CUSTOMER_NOT_FOUND = "Customer Not Found, please register";
    String ACCOUNT_OPENING_ERROR = "Error while opening Account";
    String CUSTOMER_NOT_DELETED = "Error deleting customer";
    String SUCCESSFUL_TRANSACTION = "Transaction recorded successfully";
    String CUSTOMER_DELETED = "Customer deleted successfully";
    String TRANSACTION_DELETED = "Transaction deleted successfully";
    String TRANSACTION_NOT_DELETED = "Error deleting Transaction";
}
