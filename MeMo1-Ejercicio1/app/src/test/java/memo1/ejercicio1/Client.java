package memo1.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private long DNI;
    private String name;
    private String surname;
    private String direction;
    private long bornDate;
    private List<Account> titularAccounts;
    private List<Account> coTitularAccounts;
    private Account marrige;
    private long marrigeDate;

    public Client(long DNI,String name,String surname,String direction, long bornDate){
        this.DNI = DNI;
        this.name = name;
        this.surname = surname;
        this.direction = direction;
        this.bornDate = bornDate;
        this.titularAccounts = new ArrayList<Account>();
        this.coTitularAccounts = new ArrayList<Account>();
        this.marrige = null;
        this.marrigeDate = 0;
    }

    public void addMarrige(Account marrige, long marrigeDate){
        this.marrige = marrige;
        this.marrigeDate = marrigeDate;
    }

    public boolean createAccountAsTitular(Sucursal sucursal, Long cbu, double balance, String alias){
        Account newAccount = new Account(cbu, balance, alias);
        return sucursal.addAccount(newAccount);
    }

    public boolean joinAsCoTitular(Account account){
        return this.coTitularAccounts.add(account);
    }
}
