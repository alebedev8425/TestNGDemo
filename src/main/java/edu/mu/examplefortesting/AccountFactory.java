package edu.mu.examplefortesting;

public class AccountFactory {
	/**
     * type: "savings" → SavingsAccount, anything else → BankAccount
     */
    public static BankAccount get(String type, double initial, double rate) {
        if ("savings".equalsIgnoreCase(type)) {
            return new SavingsAccount(initial, rate);
        } else {
            return new BankAccount(initial);
        }
    }

}
