package memo1.ejercicio1;


public class Account {
    private Long cbu;
    private double balance;
    private String alias;
    private Bank bank;

    public Account(Bank bank, Client owner, Long cbu, String alias) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        if(bank.checkRepeatCBU(cbu)){
            throw new IllegalArgumentException("The cbu is repeat");
        }
        if(bank.checkRepeatAlias(alias)){
            throw new IllegalArgumentException("The alias is repeat");
        }
        if(owner == null){
            throw new IllegalArgumentException("Owner can not be null");
        }
        if(bank == null){
            throw new IllegalArgumentException("bank can not be null");
        }
        this.alias = alias;
        this.cbu = cbu;
        this.balance = 0;
        this.bank = bank;
    }

    public Account(Bank bank, Client owner, Long cbu, double balance, String alias) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        if(bank.checkRepeatCBU(cbu)){
            throw new IllegalArgumentException("The cbu is repeat");
        }
        if(bank.checkRepeatAlias(alias)){
            throw new IllegalArgumentException("The alias is repeat");
        }
        if(owner == null){
            throw new NullPointerException("Owner can not be null");
        }
        if(bank == null){
            throw new NullPointerException("bank can not be null");
        }
        this.alias = alias;
        this.cbu = cbu;
        this.balance = balance;
        this.bank = bank;
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
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        return this.bank.withdraw(this, amount);
    }

    public boolean deposit(double amount) {
        return this.bank.deposit(this, amount);
    }

    public boolean transferWithCBU(double amount, long cbu){
        return this.bank.transferWithCBU(this, amount, cbu);
    }

    public boolean transferWithAlias(double amount, String alias){
        return bank.transferWithAlias(this, amount, alias);
    }

}
