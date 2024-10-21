package memo1.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Branch> branches;
    private List<Client> clients; 
    private List<Register> registers;
    private long todayDate;
    private int lastCorrelativeNumber;
    private int nowHour;

    public Bank(){
        this.branches = new ArrayList<Branch>();
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

    public Client CreateClient(long DNI,String name,String surname,String direction, long bornDate){
        if(this.checkRepeatDNI(DNI)){
            throw new IllegalArgumentException("No se puede repetir DNI");
        }
        Client newClient = new Client(DNI, name, surname, direction, bornDate);
        this.clients.add(newClient);
        return newClient;
    }

    public boolean removeClient(Client client){
        return clients.remove(client);
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

    public Bank(List<Branch> branches){
        this.branches = branches;
    }

    public List<Branch> getSucursales(){
        return this.branches;
    }

    public List<Account> getAccounts(){
        List<Account> lista = new ArrayList<Account>();
        for(Branch sucursal : this.branches) {
            for(Account account : sucursal.getAccounts()){
                lista.add(account);
            }
        }
        return lista;
    }

    public Branch createBranch(int branchNumber,  String direction, String name){
        if(this.checkRepeatBranchNumber(branchNumber)){
            throw new IllegalArgumentException("No se puede repetir nro de branch");
        }
        Branch newBranch = new Branch(branchNumber, direction, name);
        this.branches.add(newBranch);
        return newBranch;
    }

    public boolean removeBranch(Branch branch){
        return this.branches.remove(branch);
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

    public boolean checkRepeatBranchNumber(int branchNumber){
        for(Branch branch : this.branches) {
            if(branchNumber == branch.getBranchNumber()){
             return true;
            }
         }
         return false;
    }

    public boolean checkRepeatDNI(long DNI){
        for(Client client : this.clients) {
            if(DNI == client.getDNI()){
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
    public Branch searchAccountSucursalWithAlias(String alias){
        for(Branch sucursal : this.branches){
            for(Account account : this.getAccounts()) {
                if(alias == account.getAlias()){
                    return sucursal;
                }
             }
        }
        return null;
    }
    public Branch searchAccountSucursalWithCbu(long cbu){
        for(Branch sucursal : this.branches){
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
