package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

// Pruebas unitarias

class OperationsTest {
    private Client client1;
    private Client client2;
    private Branch branch1;
    private Bank bank;
    private Account account1;
    private Account account2;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.createBranch(1, "Street 15", "branch1");
        bank.createClient(12345, "Math", "Johnson",  "Street 14", 19900413);
        bank.createClient(56789, "Kamala", "Harrison", "Street 14", 19911013);
        branch1 = bank.getBranch(1);
        client1 = bank.getClient(12345);
        client2 = bank.getClient(56789);
        client1.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), 123456789L, 1000.0, "hellow12");
        client2.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), 987654321L, 1000.0, "bye14");
        account1 = bank.getAccountByCBU(123456789);
        account2 = bank.getAccountByCBU(987654321L);
    }

    @Test
    void depositShouldIncreaseBalance() {
        client1.deposit(account1, 500.0);
        assertEquals(1500.0, account1.getBalance());
    }

    @Test
    void depositShouldThrowExceptionForNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> client1.deposit(account1, -100.0));
        //assertFalse(client1.deposit(account1, -100.0));
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        assertTrue(client1.withdraw(account1, 500.0));
        assertEquals(500.0, account1.getBalance());
    }

    @Test
    void withdrawShouldThrowExceptionIfAmountExceedsBalance() {
        assertThrows(IllegalArgumentException.class, () -> client1.withdraw(account1, 1500.0));
        //assertFalse(client1.withdraw(account1,1500.0));
    }

    @Test
    void withdrawShouldThrowExceptionForNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> client1.withdraw(account1, -100.0));
        //assertFalse(client1.withdraw(account1, -100.0));
    }

    @Test
    void withdrawShouldAllowExactAmount() {
        assertTrue(client1.withdraw(account1, 1000.0));
        assertEquals(0.0, account1.getBalance());
    }

    @Test
    void transferWithCBUCorrectAmountDecreseBalanceOfSenderAccount(){
        client1.trasnferWithCBU(account1, 200.0, account2.getCbu());
        assertEquals(800.0, account1.getBalance());
    }

    @Test
    void transferWithCBUtCorrectAmountIncreaseBalanceOfRecieverAccount(){
        client1.trasnferWithCBU(account1, 200.0, account2.getCbu());
        assertEquals(1200.0, account2.getBalance());
    }

    @Test
    void transferWithAliasCorrectAmountDecreseBalanceOfDepositAccount(){ 
        client1.trasnferWithAlias(account1, 200.0, account2.getAlias());
        assertEquals(800.0, account1.getBalance());
    }

    @Test
    void transferWithAliastCorrectAmountIncreaseBalanceOfRecieverAccount(){
        client1.trasnferWithAlias(account1, 200.0, account2.getAlias());
        assertEquals(1200.0, account2.getBalance());
    }

    @Test
    void transferWithCBUNegativeAmountShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> client1.trasnferWithCBU(account1, -100.0, account2.getCbu()));
        //assertFalse(client1.trasnferWithCBU(account1, -100.0, account2.getCbu()));
    }

    @Test
    void transferWithCBUWithAccountWhoIAmNotTheOwnerThrowException() {
        assertThrows(IllegalArgumentException.class, () -> client1.trasnferWithCBU(account2, 100.0, account1.getCbu()));
        //assertFalse(client1.trasnferWithCBU(account2, 100.0, account1.getCbu()));
    }

    @Test
    void transferWithAliasWithAccountWhoIAmNotTheOwnerThrowException() {
        assertThrows(IllegalArgumentException.class, () -> client1.trasnferWithAlias(account2, 100.0, account1.getAlias()));
        //assertFalse(client1.trasnferWithAlias(account2, 100.0, account1.getAlias()));
    }

    @Test
    void transferWithCBUShouldThrowExceptionIfAmountExceedsBalance() {
        assertThrows(IllegalArgumentException.class, () -> client1.trasnferWithCBU(account1, 1001.0, account2.getCbu()));
        //assertFalse(client1.trasnferWithCBU(account1, 1001.0, account2.getCbu()));
    }

    @Test
    void transferWithAliasNegativeAmountShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> client1.trasnferWithAlias(account1, -100.0, account2.getAlias()));
        //assertFalse(client1.trasnferWithAlias(account1, -100.0, account2.getAlias()));
    }

    @Test
    void transferWithAliasShouldThrowExceptionIfAmountExceedsBalance() {
        assertThrows(IllegalArgumentException.class, () -> client1.trasnferWithCBU(account1, 1001.0, account2.getCbu()));
        //assertFalse(client1.trasnferWithCBU(account1, 1001.0, account2.getCbu()));
    }

    @Test
    void transferWithCBUShouldThrowExceptionIfAccountDontExist() {
        assertThrows(IllegalArgumentException.class, () -> client1.trasnferWithCBU(account1, 200.0, 9999));
        //assertFalse(client1.trasnferWithCBU(account1, 200.0, 9999));
    }

    @Test
    void transferWithAliasShouldThrowExceptionIfAccountDontExist() {
        assertThrows(IllegalArgumentException.class, () -> client1.trasnferWithAlias(account1, 200.0, "noExist999"));
        //assertFalse(client1.trasnferWithAlias(account1, 200.0, "noExist999"));
    }

    @Test
    void cannotDepositIntoNoOwnerOrCoOwnerAccount() {
        assertThrows(IllegalArgumentException.class, () -> client1.deposit(account2, 200.0));
        //assertFalse(client1.deposit(account2, 200.0));
    }

    @Test
    void cannoWithdrawIntoNoOwnerOrCoOwnerAccount() {
        assertThrows(IllegalArgumentException.class, () -> client1.withdraw(account2, 200.0));
        //assertFalse(client1.withdraw(account2, 200.0));
    }

    @Test
    void veifyCreationOfRegister() {
       client1.deposit(account1, 100.0);
       assertEquals(1,bank.getRegisters().size());
    }
}