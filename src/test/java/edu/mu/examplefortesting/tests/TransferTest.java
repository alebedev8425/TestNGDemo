package edu.mu.examplefortesting.tests;

import org.testng.annotations.Test;
import org.testng.annotations.*; //grab annotations from testNG
import org.testng.Assert; //grab Assert from testNG
import edu.mu.examplefortesting.BankAccount;
import edu.mu.examplefortesting.Transaction;

// Demonstrates: groups="integration", dependsOnGroups, dependsOnMethods, invocationCount, threadPoolSize

public class TransferTest {
    private BankAccount from, to;
    private Transaction svc;
    
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // create new accounts
        from = new BankAccount(1000.0);
        to   = new BankAccount(500.0);
        // create (non‐null!) service instance
        svc = new Transaction();
    }


    @Test(groups="integration",
          dependsOnGroups="unit",
          dependsOnMethods = {"testDeposit"})
    public void testBatchTransfer() {
        svc.batchTransfer(from, to, 100.0, 3);
        Assert.assertEquals(from.getBalance(), 700.0);
        Assert.assertEquals(to.getBalance(),   800.0);
    }

    @Test(invocationCount=10, threadPoolSize=5)
    public void testConcurrentDeposit() {
        from.deposit(10.0);
        System.out.println("Thread " + Thread.currentThread().getName() +
                           " -> balance: " + from.getBalance());
        // no hard assert here; just demo concurrency
    }
}