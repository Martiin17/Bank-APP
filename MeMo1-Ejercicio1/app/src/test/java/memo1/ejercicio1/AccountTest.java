package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

// Pruebas unitarias

class AccountTest {
    private Client client1;
    private Branch branch1;
    private Bank bank;
    private Account account1;
    private Account testAccount;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.createBranch(1, "Street 15", "branch1");
        bank.createClient(12345, "Math", "Johnson",  "Street 14", 19900413);
        branch1 = bank.getBranch(1);
        client1 = bank.getClient(12345);
        client1.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), 123456789L, 1000.0, "hellow12");
        account1 = bank.getAccountByCBU(123456789);
    }

    @Test
    void defaultConstructorShouldInitializeBalanceToZero() {
        client1.createAccountAsOwner(bank, branch1, 12345l, "test1");
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
        assertThrows(IllegalArgumentException.class, () -> client1.createAccountAsOwner(bank, branch1, 12345l, -100.0, "test2"));
    }

    @Test
    void constructorShouldThrowExceptionIfDontHaveBranch() {
        assertThrows(NullPointerException.class, () ->  client1.createAccountAsOwner(bank, null, 12345l, 1000.0, "test2"));
    }

    @Test
    void constructorShouldThrowExceptionIfDontHaveBank() {
        assertThrows(NullPointerException.class, () -> client1.createAccountAsOwner(null, branch1, 12345l, 1000.0, "test2"));
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
        assertThrows(IllegalArgumentException.class, () -> account1.setBalance(-100.0));
    }

    @Test
    void removeShouldThrowExcepcionIfTryToRemoveAnAccountWithFounds() {
        assertThrows(IllegalArgumentException.class, () -> branch1.removeAccountByCBU(client1, 123456789));
    }

    @Test
    void removeAccountWithNoFounds() {
        client1.createAccountAsOwner(bank,branch1,4934983284L,"goodafternoon125");
        testAccount = bank.getAccountByCBU(4934983284L);
        branch1.removeAccountByCBU(client1, 4934983284L);
        assertEquals(null, bank.getAccountByCBU(4934983284L));
    }

    @Test
    void searchForBranchWithCBUInexistentAccountShouldThrowExceptionIfAccounDontExist() {
        assertThrows(IllegalArgumentException.class, () -> bank.searchAccountBranchWithCbu(9999));
    }

    @Test
    void searchForBranchWithAliasInexistentAccountThrowExceptionIfAccounDontExist() {
        assertThrows(IllegalArgumentException.class, () -> bank.searchAccountBranchWithAlias("dontExist9999"));
    }
}
