/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/
class BankAccount {
    private boolean isOpen = false;
    private int balance;

    public boolean getIsOpen() {
        return this.isOpen;
    }
    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    private void checkIfAccountOpen() throws BankAccountActionInvalidException {
        if (!this.getIsOpen()) {
            throw new BankAccountActionInvalidException("Account closed");
        }
    }
    private void checkIfAmountPositive(int num) throws BankAccountActionInvalidException {
        if (num < 0) {
            throw new BankAccountActionInvalidException("Amount of money cannot be negative");
        }
    }
    private void checkIfAmountPositive(int num, String exceptionMessage) throws BankAccountActionInvalidException {
        if (num < 0) {
            throw new BankAccountActionInvalidException(exceptionMessage);
        }
    }
    public int getBalance() throws BankAccountActionInvalidException {
        checkIfAccountOpen();
        return this.balance;
    }
    public void setBalance(int balance) throws BankAccountActionInvalidException {
        checkIfAmountPositive(balance);
        this.balance = balance;
    }
    public void open() {
        setIsOpen(true);
    }
    public void close() {
        setIsOpen(false);
    }
    public synchronized void deposit(int amount) throws BankAccountActionInvalidException {
        checkIfAccountOpen();
        checkIfAmountPositive(amount, "Cannot deposit or withdraw negative amount");
        setBalance(amount + this.balance);
    }
    public synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
        checkIfAccountOpen();
        checkIfAmountPositive(amount, "Cannot deposit or withdraw negative amount");
        if (getBalance() == 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
        }
        if (amount > getBalance()) {
            throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        }
        setBalance(getBalance() - amount);
    }

}