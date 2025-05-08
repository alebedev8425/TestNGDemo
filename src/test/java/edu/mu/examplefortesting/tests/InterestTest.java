package edu.mu.examplefortesting.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import edu.mu.examplefortesting.BankAccount;
import edu.mu.examplefortesting.SavingsAccount;

// Demonstrates: @Test with a timeOut, successPercentage, SoftAssert

public class InterestTest {
    private final BankAccount account;

    public InterestTest(BankAccount account) {
        this.account = account;
    }

    @Test(groups = "InterestGroupDemo",timeOut=500, successPercentage=90)
    public void testAddInterest() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        double before = account.getBalance();

        if (account instanceof SavingsAccount) {
            ((SavingsAccount)account).addInterest();
            Assert.assertTrue(account.getBalance() > before, "Interest not applied");
        } else {
            // simulate interest on base account
            double rate = 0.10;
            account.deposit(before * rate);
            Assert.assertEquals(account.getBalance(), before + before * rate);
        }

        soft.assertAll();
    }
}