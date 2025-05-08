package edu.mu.examplefortesting.tests;

import org.testng.annotations.Factory;
import edu.mu.examplefortesting.BankAccount;
import edu.mu.examplefortesting.SavingsAccount;

//Demonstrates: @Factory

public class FactoryInterestTest {
    @Factory
    public Object[] createTests() {
        return new Object[] {
            new InterestTest(new SavingsAccount(100.0, 0.05)),
            new InterestTest(new BankAccount(200.0))
        };
    }
}