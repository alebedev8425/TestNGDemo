package edu.mu.examplefortesting;

public class SavingsAccount extends BankAccount {
	 private double interestRate;

	    public SavingsAccount(double initial, double interestRate) {
	        super(initial);
	        this.interestRate = interestRate;
	    }

	    /**
	     * Simulate a slow interest gaining step for testing delay with TestNG.
	     */
	    public void addInterest() throws InterruptedException {
	        Thread.sleep(200);               // simulate delay
	        double interest = balance * interestRate;
	        deposit(interest);
	    }

}
