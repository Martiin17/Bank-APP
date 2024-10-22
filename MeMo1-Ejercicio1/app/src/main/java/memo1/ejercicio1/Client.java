package memo1.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private long DNI;
    private String name;
    private String surname;
    private String direction;
    private long bornDate;
    private List<Account> ownerAccounts;
    private List<Account> coOwnerAccounts;
    private Account marrige;
    private long marrigeDate;

    public Client(long DNI,String name,String surname,String direction, long bornDate){
        this.DNI = DNI;
        this.name = name;
        this.surname = surname;
        this.direction = direction;
        this.bornDate = bornDate;
        this.ownerAccounts = new ArrayList<Account>();
        this.coOwnerAccounts = new ArrayList<Account>();
        this.marrige = null;
        this.marrigeDate = 0;
    }

    public long getDNI(){
        return this.DNI;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public String getDirection(){
        return this.direction;
    }

    public long getBornDate(){
        return this.bornDate;
    }

    public void addMarrige(Account marrige, long marrigeDate){
        this.marrige = marrige;
        this.marrigeDate = marrigeDate;
    }

    public boolean createAccountAsTitular(Bank bank, Branch sucursal, Long cbu, double balance, String alias){
        if(bank.checkRepeatAlias(alias)){
            throw new IllegalArgumentException("Alias can not be repeat");
        }
        if(bank.checkRepeatCBU(cbu)){
            throw new IllegalArgumentException("CBU can not be repeat");
        }
        Account newAccount = new Account(bank, this, cbu, balance, alias);
        this.ownerAccounts.add(newAccount);
        return sucursal.addAccount(newAccount);
    }

    public boolean createAccountAsTitular(Bank bank, Branch branch, Long cbu, String alias){
        if(branch == null){
            throw new NullPointerException("branch cannot be null");
        }
        if(bank.checkRepeatAlias(alias)){
            throw new IllegalArgumentException("Alias can not be repeat");
        }
        if(bank.checkRepeatCBU(cbu)){
            throw new IllegalArgumentException("CBU can not be repeat");
        }
        Account newAccount = new Account(bank, this, cbu, alias);
        this.ownerAccounts.add(newAccount);
        return branch.addAccount(newAccount);
    }

    public boolean joinAsCoTitular(Account account){
        return this.coOwnerAccounts.add(account);
    }

    public long getMarrigeDate(){
        if(this.marrige != null){
            return this.marrigeDate;
        }
        return 0;
    }

    public Account getOwnerAccountWithCBU(long cbu){
        for(Account account : this.ownerAccounts){
            if(account.getCbu() == cbu){
                return account;
            }
        }
        return null;
    }

    public Account getOwnerAccountWithAlias(String alias){
        for(Account account : this.ownerAccounts){
            if(account.getAlias() == alias){
                return account;
            }
        }
        return null;
    }

    public Account getCoOwnerAccountWithCBU(long cbu){
        for(Account account : this.coOwnerAccounts){
            if(account.getCbu() == cbu){
                return account;
            }
        }
        return null;
    }

    public Account getCoOwnerAccountWithAlias(String alias){
        for(Account account : this.coOwnerAccounts){
            if(account.getAlias() == alias){
                return account;
            }
        }
        return null;
    }

    public boolean checkAccountOwner(Account account){
        for(Account account1 : this.ownerAccounts){
            if(account1 == account){
                return true;
            }
        }
        for(Account account1 : this.coOwnerAccounts){
            if(account1 == account){
                return true;
            }
        }
        return false;
    }


    public boolean trasnferWithCBU(Account accountSender, double amount, long cbu){
        if(this.checkAccountOwner(accountSender) == false){
            return false;
        }
        return accountSender.transferWithCBU(amount, cbu);
    }

    public boolean trasnferWithAlias(Account accountSender, double amount, String alias){
        if(this.checkAccountOwner(accountSender) == false){
            return false;
        }
        return accountSender.transferWithAlias(amount, alias);
    }

    public boolean deposit(Account accountSender, double amount){
        if(this.checkAccountOwner(accountSender) == false){
            return false;
        }
        return accountSender.deposit(amount);
    }

    public boolean withdraw(Account accountSender, double amount){
        if(this.checkAccountOwner(accountSender) == false){
            return false;
        }
        return accountSender.withdraw(amount);
    }
}

