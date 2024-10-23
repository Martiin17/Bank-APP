package memo1.ejercicio1;

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.createBranch(1, "Street 15", "branch1");
        bank.createClient(12345, "Math", "Johnson",  "Street 14", 19900413);
        bank.createClient(56789, "Kamala", "Harrison", "Street 14", 19911013);
        Branch branch1 = bank.getBranch(1);
        Client client1 = bank.getClient(12345);
        Client client2 = bank.getClient(56789);
        client1.createAccountAsTitular(bank, bank.getBranch(branch1.getBranchNumber()), 123456789L, 1000.0, "hellow12");
        client2.createAccountAsTitular(bank, bank.getBranch(branch1.getBranchNumber()), 987654321L, 1000.0, "bye14");
        Account account1 = bank.getAccountByCBU(123456789);
        Account account2 = bank.getAccountByCBU(987654321L);

        System.out.println("client1 transfer, with his account1, to the CBU 987654321 with amount of 500.0:");
        client1.trasnferWithCBU(account1, 500.0, 987654321L);
        System.out.println("Account1 balance: " + account1.getBalance());
        System.out.println("Account2 balance: " + account2.getBalance());

        System.out.println("client2 transfer, with his account2, to the alias hellow12 with amount of 500.0:");
        client2.trasnferWithAlias(account2, 500.0, "hellow12");
        System.out.println("Account1 balance: " + account1.getBalance());
        System.out.println("Account2 balance: " + account2.getBalance());

        System.out.println("client1 join as co-owner of account2:");
        client1.joinAsCoTitular(account2);
        System.out.println("CoOwnerAccount list size: " + client1.getCoOwnerAccounts().size());
        
    }
    
}
