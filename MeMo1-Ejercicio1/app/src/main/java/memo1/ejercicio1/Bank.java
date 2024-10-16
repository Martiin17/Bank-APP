package memo1.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Sucursal> sucursales;

    public Bank(){
        this.sucursales = new ArrayList<Sucursal>();
    }

    public Bank(List<Sucursal> sucursales){
        this.sucursales = sucursales;
    }

    public List<Sucursal> getSucursales(){
        return this.sucursales;
    }

    public List<Account> getAccounts(){
        List<Account> lista = new ArrayList<Account>();
        for(Sucursal sucursal : this.sucursales) {
            for(Account account : sucursal.getAccounts()){
                lista.add(account);
            }
        }
        return lista;
    }

    public boolean addSucursal(Sucursal sucursal){
        return this.sucursales.add(sucursal);
    }

    public boolean removeSucursal(Sucursal sucursal){
        return this.sucursales.remove(sucursal);
    }

    public boolean transferWithCBU(Account accountSender, double amount, long cbu){
        for(Account account : this.getAccounts()) {
           if(cbu == account.getCbu()){
            account.setBalance(account.getBalance() + amount);
            return true;
           }
        }
        return false;
    }

    public boolean transferWithAlias(Account accountSender, double amount, String alias){
        for(Account account : this.getAccounts()) {
           if(alias == account.getAlias()){
            account.setBalance(account.getBalance() + amount);
            return true;
           }
        }
        return false;
    }
}
