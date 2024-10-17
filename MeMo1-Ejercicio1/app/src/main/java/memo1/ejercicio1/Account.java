package memo1.ejercicio1;

//Agregar excepciones
public class Account {
    private Long cbu;
    private double balance;
    private String alias;
    private Bank bank;

    public Account() {
        this.balance = 0.0;
    }

    //Patron builder?
    public Account(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = balance;
    }

    public Account(Long cbu, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        if(bank.checkRepeatCBU(cbu)){
            throw new IllegalArgumentException("The cbu is repeat");
        }
        this.cbu = cbu;
        this.balance = balance;
    }

    public Account(String alias, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        if(bank.checkRepeatAlias(alias)){
            throw new IllegalArgumentException("The alias is repeat");
        }
        this.alias = alias;
        this.balance = balance;
    }

    public Account(Long cbu, double balance, String alias) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        if(bank.checkRepeatCBU(cbu)){
            throw new IllegalArgumentException("The cbu is repeat");
        }
        if(bank.checkRepeatAlias(alias)){
            throw new IllegalArgumentException("The alias is repeat");
        }
        this.alias = alias;
        this.cbu = cbu;
        this.balance = balance;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public void setBank(Bank bank){
        this.bank = bank;
    }

    public Bank getBank(){
        return this.bank;
    }

    public String getAlias() {
        return this.alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public boolean deposit(double amount) {
        if (amount < 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean transferWithCBU(double amount, long cbu){
        if (amount <= 0 || amount > balance) {
            return false;
        }
        if (this.bank.transferWithCBU(this, amount, cbu)){
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transferWithAlias(double amount, String alias){
        if (amount <= 0 || amount > balance) {
            return false;
        }
        if (this.bank.transferWithAlias(this, amount, alias)){
            this.balance -= amount;
            return true;
        }
        return false;
    }

}
