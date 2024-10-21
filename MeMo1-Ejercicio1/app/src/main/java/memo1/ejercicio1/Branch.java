package memo1.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Branch{
    private int branchNumber;
    private String name;
    private String direction;
    private List<Account> accounts;

    public Branch(int branchNumber,  String direction, String name){
        this.accounts = new ArrayList<Account>();
        this.branchNumber = branchNumber;
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

    public int getBranchNumber(){
        return this.branchNumber;
    }

    public String getname(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getdirection(){
        return this.direction;
    }

    public void setdirection(String direction){
        this.direction = direction;
    }
}
