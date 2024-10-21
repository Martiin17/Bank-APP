package memo1.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Sucursal> sucursales;
    private List<Client> clients; //No lo uso
    private List<Register> registers;
    private long todayDate;
    private int lastCorrelativeNumber;
    private int nowHour;

    public Bank(){
        this.sucursales = new ArrayList<Sucursal>();
        this.clients = new ArrayList<Client>();
        this.registers = new ArrayList<Register>();
        this.todayDate = 20241018;
        this.lastCorrelativeNumber = 0;
        this.nowHour = 1301;
    }

    public Account getAccountByCBU(long cbu){
        for(Account account : this.getAccounts()){
            if(cbu == account.getCbu()){
                return account;
            }
        }
        return null;
    }

    public Account getAccountByAlias(String alias){
        for(Account account : this.getAccounts()){
            if(alias == account.getAlias()){
                return account;
            }
        }
        return null;
    }

    public void incrementateLastCorrelativeNumber(){
        this.lastCorrelativeNumber += 1;
    }

    public void setTodayDate(long date){
        this.todayDate = date;
    }

    public void setHour(int hour){
        this.nowHour = hour;
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
        this.checkValidAmount(amount, accountSender.getBalance());
        for(Account account : this.getAccounts()) {
           if(cbu == account.getCbu()){
            accountSender.setBalance(accountSender.getBalance() - amount);
            account.setBalance(account.getBalance() + amount);
            List<Account> registerAccounts = new ArrayList<Account>();
            registerAccounts.add(accountSender);
            registerAccounts.add(account);
            Register register = new Register(this.lastCorrelativeNumber, this.todayDate, this.nowHour, "TransaccionWithCBU", amount, registerAccounts);
            this.registers.add(register);
            this.incrementateLastCorrelativeNumber();
            return true;
           }
        }
        return false;
    }

    public boolean transferWithAlias(Account accountSender, double amount, String alias){
        this.checkValidAmount(amount, accountSender.getBalance());
        for(Account account : this.getAccounts()) {
           if(alias == account.getAlias()){
            accountSender.setBalance(accountSender.getBalance() - amount);
            account.setBalance(account.getBalance() + amount);
            List<Account> registerAccounts = new ArrayList<Account>();
            registerAccounts.add(accountSender);
            registerAccounts.add(account);
            Register register = new Register(this.lastCorrelativeNumber, this.todayDate, this.nowHour, "TransaccionWithAlias", amount, registerAccounts);
            this.registers.add(register);
            this.incrementateLastCorrelativeNumber();
            return true;
           }
        }
        return false;
    }

    public boolean checkRepeatCBU(long newCBU){
        for(Account account : this.getAccounts()) {
            if(newCBU == account.getCbu()){
             return true;
            }
         }
         return false;
    }

    public boolean checkRepeatAlias(String alias){
        for(Account account : this.getAccounts()) {
            if(alias == account.getAlias()){
             return true;
            }
         }
         return false;
    }

    public boolean checkValidAmount(Double amount, double balance){
        if (amount <= 0 || amount > balance) {
            return false;
        }
        return true;
    }
    public Sucursal searchAccountSucursalWithAlias(String alias){
        for(Sucursal sucursal : this.sucursales){
            for(Account account : this.getAccounts()) {
                if(alias == account.getAlias()){
                    return sucursal;
                }
             }
        }
        return null;
    }
    public Sucursal searchAccountSucursalWithCbu(long cbu){
        for(Sucursal sucursal : this.sucursales){
            for(Account account : this.getAccounts()) {
                if(cbu == account.getCbu()){
                    return sucursal;
                }
             }
        }
        return null;
    }

    public long searchMarrigeDate(Client client){ //Devuelve 0 si no se caso
        return client.getMarrigeDate();
    }
}
