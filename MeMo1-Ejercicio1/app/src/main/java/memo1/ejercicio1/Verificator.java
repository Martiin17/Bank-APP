package memo1.ejercicio1;

import java.util.ArrayList;
import java.util.List;

//Borrar
public class Verificator {
    private List<Account> accounts;

    public Verificator(){
        this.accounts = new ArrayList<Account>();
    }

    public Verificator(List<Account> accounts){
        this.accounts = accounts;
    }

    public boolean addAccount(Account account){
        return this.accounts.add(account);
    }

    public boolean removeAccount(Account account){
        return this.accounts.remove(account);
    }

    public boolean verificateCBU(double amount, long cbu){
        for(Account account : accounts) {
           if(cbu == account.getCbu()){
            account.setBalance(account.getBalance() + amount);
            return true;
           }
        }
        return false;
    }

}