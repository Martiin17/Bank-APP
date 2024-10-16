package memo1.ejercicio1;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;
    private List<Sucursal> sucursales;

    public Bank(){
        this.accounts = new ArrayList<Account>();
        this.sucursales = new ArrayList<Sucursal>();
    }

    public Bank(List<Account> accounts, List<Sucursal> sucursales){
        this.accounts = accounts;
        this.sucursales = sucursales;
    }

    public boolean addAccount(Account account){
        return this.accounts.add(account);
    }

    public boolean removeAccount(Account account){
        return this.accounts.remove(account);
    }

    public boolean addSucursal(Sucursal sucursal){
        return this.sucursales.add(sucursal);
    }

    public boolean removeSucursal(Sucursal sucursal){
        return this.sucursales.remove(sucursal);
    }

    public boolean transferWithCBU(Account accountSend, double amount, long cbu){
        for(Account account : accounts) {
           if(cbu == account.getCbu()){
            account.setBalance(account.getBalance() + amount);
            return true;
           }
        }
        return false;
    }

    public boolean transferWithAlias(Account accountSend, double amount, String alias){
        for(Account account : accounts) {
           if(alias == account.getAlias()){
            account.setBalance(account.getBalance() + amount);
            return true;
           }
        }
        return false;
    }
}
