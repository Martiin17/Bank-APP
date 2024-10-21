package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

// Pruebas unitarias

class AccountTest {
    //private Account account1;
    //private Account account2;
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
        bank.CreateClient(12345, "Math", "Johnson",  "Street 14", 19900413);
        bank.CreateClient(56789, "Kamala", "Harrison", "Street 14", 19911013);
        branch1 = bank.getBranch(1);
        client1 = bank.getClient(12345);
        client2 = bank.getClient(56789);
        client1.createAccountAsTitular(bank, bank.getBranch(branch1.getBranchNumber()), 123456789L, 1000.0, "hellow12");
        client2.createAccountAsTitular(bank, bank.getBranch(branch1.getBranchNumber()), 987654321L, 1000.0, "bye14");
        account1 = bank.getAccountByCBU(123456789);
        account2 = bank.getAccountByCBU(987654321L);
    }

    @Test
    void defaultConstructorShouldInitializeBalanceToZero() {
        client1.createAccountAsTitular(bank, branch1, 12345l, "test1");
        Account account = bank.getAccountByCBU(12345);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void constructorShouldSetBalanceCorrectly() {
        Account account = bank.getAccountByCBU(123456789);
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionIfBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> client1.createAccountAsTitular(bank, branch1, 12345l, -100.0, "test2"));
    }

    @Test
    void constructorShouldThrowExceptionIfDontHaveBranch() {
        assertThrows(NullPointerException.class, () ->  client1.createAccountAsTitular(bank, null, 12345l, 1000.0, "test2"));
    }

    @Test
    void constructorShouldThrowExceptionIfDontHaveBank() {
        assertThrows(NullPointerException.class, () -> client1.createAccountAsTitular(null, branch1, 12345l, 1000.0, "test2"));
    }

    @Test
    void constructorShouldThrowExceptionIfDontHaveOwner() {
        assertThrows(IllegalArgumentException.class, () -> new Account(bank, null, 12345l, "test12"));
    }

    @Test
    void constructorWithCBUAndAliasShouldInitializeCorrectly() {
        assertEquals(123456789L, account1.getCbu());
        assertEquals(1000.0, account1.getBalance());
        assertEquals("hellow12", account1.getAlias());
        assertEquals(bank, account1.getBank());
        assertEquals(branch1, bank.getBranch(branch1.getBranchNumber()));
    }

    @Test
    void setBalanceShouldThrowExceptionIfBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> account1.setBalance(-1.0));
    }

    @Test
    void createAccountWithRepeatCBUShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> client1.createAccountAsTitular(bank, branch1, 123456789L, "goodmorning14"));
    }

    @Test
    void createAccountWithRepeatAliasShoulThrowException() {
        assertThrows(IllegalArgumentException.class, () ->client1.createAccountAsTitular(bank, branch1, 999999999L, "hellow12"));
    }

    @Test
    void depositShouldIncreaseBalance() {
        client1.deposit(account1, 500.0);
        assertEquals(1500.0, account1.getBalance());
    }

    @Test
    void depositShouldReturnFalseForNegativeAmount() {
        assertFalse(client1.deposit(account1, -100.0));
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        assertTrue(client1.withdraw(account1, 500.0));
        assertEquals(500.0, account1.getBalance());
    }

    @Test
    void withdrawShouldReturnFalseIfAmountExceedsBalance() {
        assertFalse(client1.withdraw(account1,1500.0));
    }

    @Test
    void withdrawShouldReturnFalseForNegativeAmount() {
        assertFalse(client1.withdraw(account1, -100.0));
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
    void transferWithCBUNegativeAmountShouldReturnFalse() {
        assertFalse(client1.trasnferWithCBU(account1, -100.0, account2.getCbu()));
    }

    @Test
    void transferWithCBUWithAccountWhoIAmNotTheOwnerReturnFalse() {
        assertFalse(client1.trasnferWithCBU(account2, 100.0, account1.getCbu()));
    }

    @Test
    void transferWithAliasWithAccountWhoIAmNotTheOwnerReturnFalse() {
        assertFalse(client1.trasnferWithAlias(account2, 100.0, account1.getAlias()));
    }

    @Test
    void transferWithCBUShouldReturnFalseIfAmountExceedsBalance() {
        assertFalse(client1.trasnferWithCBU(account1, 1001.0, account2.getCbu()));
    }

    @Test
    void transferWithAliasNegativeAmountShouldReturnFalse() {
        assertFalse(client1.trasnferWithAlias(account1, -100.0, account2.getAlias()));
    }

    @Test
    void transferWithAliasShouldReturnFalseIfAmountExceedsBalance() {
        assertFalse(client1.trasnferWithCBU(account1, 1001.0, account2.getCbu()));
    }

    @Test
    void transferWithCBUShouldReturnFalseIfAccountDontExist() {
        assertFalse(client1.trasnferWithCBU(account1, 200.0, 9999));
    }

    @Test
    void transferWithAliasShouldReturnFalseIfAccountDontExist() {
        assertFalse(client1.trasnferWithAlias(account1, 200.0, "noExist999"));
    }

    @Test
    void searchForMarriageDateWithInexistentDNIShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.searchMarrigeDate(9999));
    }

    @Test
    void searchForMarriageDateWithASinglePersonShouldReturnZero() {
        assertEquals(0, bank.searchMarrigeDate(client1.getDNI()));
    }

    @Test
    void searchForBranchWithCBUInexistentAccountShouldThrowExceptionIfAccounDontExist() {
        assertThrows(IllegalArgumentException.class, () -> bank.searchAccountSucursalWithCbu(9999));
    }

    @Test
    void searchForBranchWithAliasInexistentAccountThrowExceptionIfAccounDontExist() {
        assertThrows(IllegalArgumentException.class, () -> bank.searchAccountSucursalWithAlias("dontExist9999"));
    }

    @Test
    void createBranchWithRepeatBranchNumberShoulThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.createBranch(branch1.getBranchNumber(), "Street 15", "branch2"));
    }

    @Test
    void RemoveBranchWithClientsShouldReturnFalse() {
        assertThrows(IllegalArgumentException.class, () -> bank.removeBranch(branch1));
    }

}
