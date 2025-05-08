package edu.mu.examplefortesting.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import edu.mu.examplefortesting.BankAccount;
import edu.mu.examplefortesting.Transaction;

public class FailingTests {

    private BankAccount account;
    private Transaction svc;

    @BeforeMethod
    public void setUp() {
        // give each test a fresh account and service
        account = new BankAccount(100.0);
        svc     = new Transaction();
    }

    /**
     * Fail We tell TestNG we expect an IllegalArgumentException
     * if we withdraw a negative amount—but our code only throws
     * when amt > balance, so no exception is ever thrown here.
     */
    @Test(groups="failureDemo",expectedExceptions = IllegalArgumentException.class)
    public void testWithdrawNegativeAmount() {
        account.withdraw(-10.0);
        // no exception will happen → TestNG will mark this as a failure
    }

    /**
     * Fail: We transfer money and then assert the wrong expected balance.
     * After transferring 50 three times from 200 → 100, the "from" account
     * will have 200 - 3*50 = 50. Here we assert 100, so it fails.
     */
    @Test(groups="failureDemo")
    public void testBatchTransferWrongAssertion() {
        BankAccount from = new BankAccount(200.0);
        BankAccount to   = new BankAccount(100.0);

        // actually leaves from.balance = 50.0
        svc.batchTransfer(from, to, 50.0, 3);

        // this assertion is intentionally wrong:
        Assert.assertEquals(
            from.getBalance(), 
            100.0, 
            "Expected from.balance = 100.0, but got " + from.getBalance()
        );
    }
}