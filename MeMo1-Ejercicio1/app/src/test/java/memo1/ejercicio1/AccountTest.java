package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

// Pruebas unitarias

class AccountTest {
    private Account account1;
    private Account account2;
    private Sucursal sucursal1;
    private Bank bank;

    @BeforeEach
    void setUp() {
        account1 = new Account(1234L, 1000.0);
        account2 = new Account(5678L, 1000.0);
        sucursal1 = new Sucursal(1, "PrimeraJunta 829");
        bank = new Bank();
        bank.addSucursal(sucursal1);
        sucursal1.addAccount(account1);
        sucursal1.addAccount(account2);
        account1.setBank(bank);
        account2.setBank(bank);
    }

    @Test
    void defaultConstructorShouldInitializeBalanceToZero() {
        Account account = new Account();
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void constructorShouldSetBalanceCorrectly() {
        Account account = new Account(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionIfBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Account(-50.0));
    }

    @Test
    void constructorWithCbuShouldInitializeCorrectly() {
        Account account = new Account(123456789L, 100.0);
        assertEquals(123456789L, account.getCbu());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void setBalanceShouldThrowExceptionIfBalanceIsNegative() {
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> account.setBalance(-1.0));
    }

    @Test
    void depositShouldIncreaseBalance() {
        Account account = new Account();
        account.deposit(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void depositShouldReturnFalseForNegativeAmount() {
        Account account = new Account();
        assertFalse(account.deposit(-10.0));
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        Account account = new Account(100.0);
        assertTrue(account.withdraw(50.0));
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void withdrawShouldReturnFalseIfAmountExceedsBalance() {
        Account account = new Account(100.0);
        assertFalse(account.withdraw(150.0));
    }

    @Test
    void withdrawShouldReturnFalseForNegativeAmount() {
        Account account = new Account(100.0);
        assertFalse(account.withdraw(-10.0));
    }

    @Test
    void withdrawShouldAllowExactAmount() {
        Account account = new Account(100.0);
        assertTrue(account.withdraw(100.0));
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void depositCorrectAmountDecreseBalanceOfDepositAccount(){
        account1.transferWithCBU(200, account2.getCbu());
        assertEquals(800.0, account1.getBalance());
    }

    @Test
    void depositCorrectAmountIncreaseBalanceOfRecieverAccount(){
        account1.transferWithCBU(200, account2.getCbu());
        assertEquals(1200.0, account2.getBalance());
    }

    @Test
    void depositNegativeAmountShouldReturnFalse() {
        assertFalse(account1.transferWithCBU(-100, account2.getCbu()));
    }

    @Test
    void depositShouldReturnFalseIfAmountExceedsBalance() {
        assertFalse(account1.transferWithCBU(1001, account2.getCbu()));
    }

    @Test
    void transferShouldReturnFalseIfAccountDontExist() {
        assertFalse(account1.transferWithCBU(200, 9999));
    }
}
