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
            if(alias.equals(account.getAlias())){
                return account;
            }
        }
        return null;
    }

    public Client createClient(long DNI,String name,String surname,String direction, long bornDate){
        if(this.checkRepeatDNI(DNI)){
            throw new IllegalArgumentException("Cannot repeat DNI");
        }
        Client newClient = new Client(DNI, name, surname, direction, bornDate);
        this.clients.add(newClient);
        return newClient;
    }

    public boolean removeClient(long DNI){
        for(Client client : this.clients){
            if(client.getDNI() == DNI){
                this.clients.remove(client);
                return true;
            }
        }
        throw new IllegalArgumentException("Cannot found this Client");
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

    public List<Register> getRegisters(){
        return this.registers;
    }

    public Branch getBranch(int branchNumber){
        for(Branch branch : this.branches) {
            if(branch.getBranchNumber() == branchNumber){
                return branch;
            }
        }
        return null;
    }

    public Client getClient(long DNI){
        for(Client client : this.clients) {
            if(client.getDNI() == DNI){
                return client;
            }
        }
        return null;
    }

    public List<Account> getAccounts(){
        List<Account> lista = new ArrayList<Account>();
        for(Branch branch : this.branches) {
            for(Account account : branch.getAccounts()){
                lista.add(account);
            }
        }
        return lista;
    }

    public Branch createBranch(int branchNumber,  String direction, String name){
        if(this.checkRepeatBranchNumber(branchNumber)){
            throw new IllegalArgumentException("Can not repeat branch number");
        }
        Branch newBranch = new Branch(branchNumber, direction, name);
        this.branches.add(newBranch);
        return newBranch;
    }

    public boolean removeBranch(Branch branch){
        if(branch.getAccounts().size() == 0){
            return this.branches.remove(branch);
        }
       throw new IllegalArgumentException("Cannot remove branch with accounts");
    }

    public boolean transferWithCBU(Account accountSender, double amount, long cbu){
        if(!this.checkValidAmount(amount, accountSender.getBalance())){
            return false;
        }
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
        if(!this.checkValidAmount(amount, accountSender.getBalance())){
            return false;
        }
        for(Account account : this.getAccounts()) {
           if(alias.equals(account.getAlias())){
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

    public boolean deposit(Account accountSender, double amount){
        if(!this.checkValidAmount(amount, accountSender.getBalance())){
            return false;
        }
        accountSender.setBalance(accountSender.getBalance() + amount);
        List<Account> registerAccounts = new ArrayList<Account>();
        registerAccounts.add(accountSender);
        Register register = new Register(this.lastCorrelativeNumber, this.todayDate, this.nowHour, "deposit", amount, registerAccounts);
        this.registers.add(register);
        this.incrementateLastCorrelativeNumber();
        return true;
    }

    public boolean withdraw(Account accountSender, double amount){
        if(!this.checkValidAmount(amount, accountSender.getBalance())){
            return false;
        }
        accountSender.setBalance(accountSender.getBalance() - amount);
        List<Account> registerAccounts = new ArrayList<Account>();
        registerAccounts.add(accountSender);
        Register register = new Register(this.lastCorrelativeNumber, this.todayDate, this.nowHour, "withdraw", amount, registerAccounts);
        this.registers.add(register);
        this.incrementateLastCorrelativeNumber();
        return true;
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
            if(alias.equals(account.getAlias())){
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
        throw new IllegalArgumentException("Dont exist this alias");
    }
    public Branch searchAccountSucursalWithCbu(long cbu){
        for(Branch sucursal : this.branches){
            for(Account account : this.getAccounts()) {
                if(cbu == account.getCbu()){
                    return sucursal;
                }
             }
        }
        throw new IllegalArgumentException("Dont exist this cbu");
    }

    public long searchMarrigeDate(long DNI){ //Devuelve 0 si no se caso
        Client client  = this.getClient(DNI);
        if(client == null){
            throw new IllegalArgumentException("Dont exist this DNI");
        }
        return client.getMarrigeDate();
    }
}
