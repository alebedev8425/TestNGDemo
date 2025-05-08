package edu.mu.examplefortesting;

public class BankAccount {
	protected double balance;

    public BankAccount(double initial) {
        this.balance = initial;
    }

    public void deposit(double amt) {
        balance += amt;
    }

    public void withdraw(double amt) {
        if (amt > balance) {
            throw new IllegalArgumentException("You are withdrawing more than your balance. This is not allowed.");
        }
        balance -= amt;
    }

    public void transferTo(BankAccount other, double amt) {
        withdraw(amt);
        other.deposit(amt);
    }

    public double getBalance() {
        return balance;
    }

}
