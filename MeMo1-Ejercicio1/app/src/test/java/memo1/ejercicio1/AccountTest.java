package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

// Pruebas unitarias

class AccountTest {
    private Account account1;
    private Account account2;
    private Client client1;
    private Client client2;
    private Sucursal sucursal1;
    private Bank bank;

    @BeforeEach
    void setUp() {
        client1 = new Client(12345, "Math", "Johnson", "Street 14", 19900413);
        client2 = new Client(56789, "Kamala", "Harrison", "Street 14", 19911013);
        bank = new Bank();
        sucursal1 = new Sucursal(1, "PrimeraJunta 829", "sucursal1");
        bank.addSucursal(sucursal1);
        client1.createAccountAsTitular(bank, sucursal1, 123456789L, 1000.0, "hellow12");
        client2.createAccountAsTitular(bank, sucursal1, 987654321L, 1000.0, "bye14");
    }

    @Test
    void defaultConstructorShouldInitializeBalanceToZero() {
        client1.createAccountAsTitular(bank, sucursal1, 123456789l, "hellow12");
        Account account = bank.getAccountByCBU(123456789);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void constructorShouldSetBalanceCorrectly() {
        client1.createAccountAsTitular(bank, sucursal1, 123456789l, 1000.0, "hellow12");
        Account account = bank.getAccountByCBU(123456789);
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionIfBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Account(bank, client1, 123456789l, -100.0, "hellow12"));
    }

    @Test
    void constructorShouldThrowExceptionIfDontHaveOwner() {
        assertThrows(IllegalArgumentException.class, () -> new Account(bank, null, 123456789l, 1000.0, "hellow12"));
    }

    @Test
    void constructorShouldThrowExceptionIfDontHaveBank() {
        assertThrows(IllegalArgumentException.class, () -> new Account(null, client1, 123456789l, 1000.0, "hellow12"));
    }

    @Test
    void constructorWithCBUAndAliasShouldInitializeCorrectly() {
        client1.createAccountAsTitular(bank, sucursal1, 123456789l, 1000.0, "hellow12");
        Account account = bank.getAccountByCBU(123456789);
        assertEquals(123456789L, account.getCbu());
        assertEquals(1000.0, account.getBalance());
        assertEquals("hellow12", account.getAlias());
        assertEquals(bank, account.getBank());
    }

    @Test
    void setBalanceShouldThrowExceptionIfBalanceIsNegative() {
        Account account = bank.getAccountByCBU(123456789);
        assertThrows(IllegalArgumentException.class, () -> account.setBalance(-1.0));
    }

    @Test
    void createAccountWithRepeatCBUShouldReturnFalse() {
        Account account = bank.getAccountByCBU(123456789);
        assertThrows(IllegalArgumentException.class, () -> account.setBalance(-1.0));
    }

    @Test
    void createAccountWithRepeatAliasShouldReturnFalse() {
        Account account = bank.getAccountByCBU(123456789);
        assertThrows(IllegalArgumentException.class, () -> account.setBalance(-1.0));
    }

    @Test
    void depositShouldIncreaseBalance() {
        Account account = bank.getAccountByCBU(123456789);
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    void depositShouldReturnFalseForNegativeAmount() {
        Account account = bank.getAccountByCBU(123456789);
        assertFalse(account.deposit(-100.0));
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        Account account = bank.getAccountByCBU(123456789);
        assertTrue(account.withdraw(500.0));
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void withdrawShouldReturnFalseIfAmountExceedsBalance() {
        Account account = bank.getAccountByCBU(123456789);
        assertFalse(account.withdraw(1500.0));
    }

    @Test
    void withdrawShouldReturnFalseForNegativeAmount() {
        Account account = bank.getAccountByCBU(123456789);
        assertFalse(account.withdraw(-100.0));
    }

    @Test
    void withdrawShouldAllowExactAmount() {
        Account account = bank.getAccountByCBU(123456789);
        assertTrue(account.withdraw(1000.0));
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void transferWithCBUCorrectAmountDecreseBalanceOfDepositAccount(){ //Hasta aca llegue
        Account account = bank.getAccountByCBU(123456789);
        account1.transferWithCBU(200, account2.getCbu());
        assertEquals(800.0, account1.getBalance());
    }

    @Test
    void transferWithCBUtCorrectAmountIncreaseBalanceOfRecieverAccount(){
        account1.transferWithCBU(200, account2.getCbu());
        assertEquals(1200.0, account2.getBalance());
    }

    @Test
    void transferWithAliasCorrectAmountDecreseBalanceOfDepositAccount(){ //Hasta aca llegue
        Account account = bank.getAccountByCBU(123456789);
        account1.transferWithCBU(200, account2.getCbu());
        assertEquals(800.0, account1.getBalance());
    }

    @Test
    void transferWithAliastCorrectAmountIncreaseBalanceOfRecieverAccount(){
        account1.transferWithCBU(200, account2.getCbu());
        assertEquals(1200.0, account2.getBalance());
    }

    @Test
    void transferWithCBUNegativeAmountShouldReturnFalse() {
        assertFalse(account1.transferWithCBU(-100, account2.getCbu()));
    }

    @Test
    void transferWithCBUShouldReturnFalseIfAmountExceedsBalance() {
        assertFalse(account1.transferWithCBU(1001, account2.getCbu()));
    }

    @Test
    void transferWithAliasNegativeAmountShouldReturnFalse() {
        assertFalse(account1.transferWithCBU(-100, account2.getCbu()));
    }

    @Test
    void transferWithAliasShouldReturnFalseIfAmountExceedsBalance() {
        assertFalse(account1.transferWithCBU(1001, account2.getCbu()));
    }

    @Test
    void transferWithCBUShouldReturnFalseIfAccountDontExist() {
        assertFalse(account1.transferWithCBU(200, 9999));
    }

    @Test
    void transferWithAliasShouldReturnFalseIfAccountDontExist() {
        assertFalse(account1.transferWithCBU(200, 9999));
    }

    @Test
    void searchForMarriageInexistentAccountReturnFalseIfAccounDontExist() {
        assertFalse(account1.transferWithCBU(200, 9999));
    }

    @Test
    void searchForBranchWithCBUInexistentAccountReturnFalseIfAccounDontExist() {
        assertFalse(account1.transferWithCBU(200, 9999));
    }

    @Test
    void searchForBranchWithAliasInexistentAccountReturnFalseIfAccounDontExist() {
        assertFalse(account1.transferWithCBU(200, 9999));
    }

    @Test
    void createBranchWithRepeatBranchNumberShouldReturnFalse() {
        assertFalse(account1.transferWithCBU(200, 9999));
    }

    @Test
    void RemoveBranchWithClientsShouldReturnFalse() {
        assertFalse(account1.transferWithCBU(200, 9999));
    }


}
