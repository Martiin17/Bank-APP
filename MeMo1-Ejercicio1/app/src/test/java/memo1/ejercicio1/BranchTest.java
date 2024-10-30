package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

// Pruebas unitarias

class BranchTest {
    private Client client1;
    //private Client client2;
    private Branch branch1;
    private Bank bank;
    private Account account1;
    //private Account account2;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.createBranch(1, "Street 15", "branch1");
        bank.createClient(12345, "Math", "Johnson",  "Street 14", 19900413);
        //bank.createClient(56789, "Kamala", "Harrison", "Street 14", 19911013);
        branch1 = bank.getBranch(1);
        client1 = bank.getClient(12345);
        //client2 = bank.getClient(56789);
        client1.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), 123456789L, 1000.0, "hellow12");
        //client2.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), 987654321L, 1000.0, "bye14");
        account1 = bank.getAccountByCBU(123456789);
        //account2 = bank.getAccountByCBU(987654321L);
    }

    @Test
    void searchForBranchWithCBUInexistentAccountShouldThrowExceptionIfAccounDontExist() {
        assertThrows(IllegalArgumentException.class, () -> bank.searchAccountBranchWithCbu(9999));
    }

    @Test
    void searchForBranchWithAliasInexistentAccountThrowExceptionIfAccounDontExist() {
        assertThrows(IllegalArgumentException.class, () -> bank.searchAccountBranchWithAlias("dontExist9999"));
    }

    @Test
    void createBranchWithRepeatBranchNumberShoulThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.createBranch(branch1.getBranchNumber(), "Street 15", "branch2"));
    }

    @Test
    void RemoveBranchWithAccountsShouldReturnFalse() {
        assertThrows(IllegalArgumentException.class, () -> bank.removeBranch(branch1));
    }
}