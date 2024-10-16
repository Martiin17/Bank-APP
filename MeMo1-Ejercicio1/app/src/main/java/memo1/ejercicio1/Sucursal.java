package memo1.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {
    private int sucursalNumber;
    private String name;
    private String direction;
    private List<Account> accounts;

    public Sucursal(int sucursalNumber,  String direction){
        this.sucursalNumber = sucursalNumber;
        this.direction = direction;
        this.accounts = new ArrayList<Account>(); //Poner patron de diseño builder para esto
    }

    public Sucursal(int sucursalNumber,  String direction, String name){
        this.sucursalNumber = sucursalNumber;
        this.direction = direction;
        this.name = name;
    }

    public boolean addAccount(Account account){
        return this.accounts.add(account);
    }

    public boolean removeAccount(Account account){
        return this.accounts.remove(account);
    }

    public List<Account> getAccounts(){
        return this.accounts;
    }

    private int getSucursalNumber(){
        return this.sucursalNumber;
    }

    private void setSucursalNumber(int sucursalNumber){
        this.sucursalNumber = sucursalNumber;
    }

    private String getname(){
        return this.name;
    }

    private void setName(String name){
        this.name = name;
    }

    private String getdirection(){
        return this.direction;
    }

    private void setdirection(String direction){
        this.direction = direction;
    }
}
