class BankAccount {
    private boolean isOpen = false;
    private int balance;


    private void checkIfAccountOpen() throws BankAccountActionInvalidException {
        if (!this.isOpen) {
            throw new BankAccountActionInvalidException("Account closed");
        }
    }

    private void checkIfAmountPositive(int num) throws BankAccountActionInvalidException {
        if (num < 0) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
    }

    public int getBalance() throws BankAccountActionInvalidException {
        checkIfAccountOpen();
        return this.balance;
    }

    private void setBalance(int balance) {
        this.balance = balance;
    }

    public void open() {
        this.isOpen = true;
    }

    public void close() {
        this.isOpen = false;
    }

    public synchronized void deposit(int amount) throws BankAccountActionInvalidException {
        checkIfAccountOpen();
        checkIfAmountPositive(amount);
        setBalance(amount + this.balance);
    }

    public synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
        checkIfAccountOpen();
        checkIfAmountPositive(amount);
        if (getBalance() == 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
        }
        if (amount > getBalance()) {
            throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        }
        setBalance(getBalance() - amount);
    }

}