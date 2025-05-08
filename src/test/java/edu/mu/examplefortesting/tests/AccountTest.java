package edu.mu.examplefortesting.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*; //grab annotations from testNG
import org.testng.Assert; //grab Assert from testNG
import edu.mu.examplefortesting.BankAccount;
import edu.mu.examplefortesting.AccountFactory;

/* Demonstrates: @BeforeSuite, @BeforeClass, @BeforeMethod, @After…, groups, 
 * @DataProvider, @Parameters, expectedExceptions, enabled=false.
*/

public class AccountTest {
    private BankAccount account;

    @BeforeSuite(alwaysRun=true)
    public void beforeSuite() {
        System.out.println("BeforeSuite: init in-memory DB");
    }

    @AfterSuite(alwaysRun=true)
    public void afterSuite() {
        System.out.println("AfterSuite: cleanup DB");
    }

    @BeforeClass(alwaysRun=true)
    public void beforeClass() {
        System.out.println("BeforeClass: setting up class resources");
    }

    @AfterClass(alwaysRun=true)
    public void afterClass() {
        System.out.println("AfterClass: tearing down class resources");
    }

    @BeforeMethod(alwaysRun=true)
    public void setup() {
        account = AccountFactory.get("default", 100.0, 0.0);
    }

    @AfterMethod(alwaysRun=true)
    public void teardown() {
        System.out.println("AfterMethod: test complete");
    }

    @Test(groups="unit", dataProvider="amounts")
    public void testDeposit(double amt) {
        account.deposit(amt);
        Assert.assertEquals(account.getBalance(), 100.0 + amt);
    }

    @Test(groups="unit", dataProvider="amounts")
    public void testWithdraw(double amt) {
        account.withdraw(amt);
        Assert.assertEquals(account.getBalance(), 100.0 - amt);
    }

    @DataProvider(name="amounts")
    public Object[][] amounts() {
        return new Object[][] {{20.0}, {50.0}};
    }

    @Parameters("overdraftLimit")
    @Test(groups="unit", expectedExceptions=IllegalArgumentException.class)
    public void testOverdraft(@Optional("50.0")double overdraftLimit) {
        // should throw because amt > balance
        account.withdraw(100.0 + overdraftLimit);
    }

    @Test(enabled=false, groups= "unit")
    public void disabledTest() {
        Assert.fail("This is a disabled test");
    }
}