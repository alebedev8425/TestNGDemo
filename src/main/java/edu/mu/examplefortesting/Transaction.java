package edu.mu.examplefortesting;

public class Transaction {
	
	/**
     * Do the same transfer over and over to test concurrency, and parallelism if
     *  a batch transfer is split over multiple threads
     */
    public void batchTransfer(BankAccount from, BankAccount to, double amt, int times) {
        for (int i = 0; i < times; i++) {
            from.transferTo(to, amt);
        }
    }

}
