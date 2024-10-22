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

        client1.deposit(account1, 500.0);
        System.out.println(account1.getBalance());
        /* 
        // Crear una instancia de Account usando el constructor sin argumentos
        Account account1 = new Account();
        account1.setCbu(123456789L); // Asignar un CBU
        account1.setBalance(1000.0); // Establecer el balance inicial

        // Crear una instancia de Account usando el constructor con saldo inicial
        Account account2 = new Account(987654321L, 500.0);

        // Realizar operaciones de depósito y retiro
        account1.deposit(200.0);  // Depositar 200 en la cuenta 1
        boolean successWithdraw = account1.withdraw(300.0);  // Retirar 300 de la cuenta 1

        account2.deposit(100.0);  // Depositar 100 en la cuenta 2
        boolean successWithdraw2 = account2.withdraw(700.0);  // Intentar retirar 700 de la cuenta 2 (debería fallar)

        // Imprimir detalles de las cuentas
        System.out.println("Cuenta 1:");
        System.out.println("CBU: " + account1.getCbu());
        System.out.println("Balance: " + account1.getBalance());

        System.out.println("Cuenta 2:");
        System.out.println("CBU: " + account2.getCbu());
        System.out.println("Balance: " + account2.getBalance());

        // Verificar si las operaciones fueron exitosas
        System.out.println("Retiro en cuenta 1 fue " + (successWithdraw ? "exitoso" : "fallido"));
        System.out.println("Retiro en cuenta 2 fue " + (successWithdraw2 ? "exitoso" : "fallido"));

        //Ejercicio2
        Account cuenta1 = new Account(1234L, 1000.0);
        Account cuenta2 = new Account(5678L, 1000.0);
        Branch sucursal1 = new Branch(1, "PrimeraJunta  829", "sucursal1");
        Bank bank = new Bank();
        bank.addSucursal(sucursal1);
        sucursal1.addAccount(cuenta1);
        sucursal1.addAccount(cuenta2);
        cuenta1.setBank(bank);
        cuenta2.setBank(bank);

        cuenta1.transferWithCBU(200, 5678);

        System.out.println("El saldo de la cuenta1 es: " + cuenta1.getBalance());
        System.out.println("El saldo de la cuenta2 es: " + cuenta2.getBalance());
    */
    }
    
}
