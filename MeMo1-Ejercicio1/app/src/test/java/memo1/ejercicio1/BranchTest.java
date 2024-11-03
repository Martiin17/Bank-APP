package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

// Pruebas unitarias

class BranchTest {
    private Client client1;
    //private Client client2;
    private Branch branch1;
    private Branch testBranch;
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
    void createBranch() {
        bank.createBranch(458, "La Rioja 500", "new branch");
        testBranch = bank.getBranch(458);
        assertEquals("La Rioja 500", testBranch.getDirection());
        assertEquals("new branch", testBranch.getName());
    }

    @Test
    void createBranchWithRepeatBranchNumberShoulThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.createBranch(branch1.getBranchNumber(), "Street 15", "branch2"));
    }

    @Test
    void RemoveBranchWithAccountsShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.removeBranch(branch1));
    }

    @Test
    void RemoveBranchWithoutAccountsShouldReturnTrue() {
        client1.withdraw(account1, 1000.0); //Dejamos la cuenta en 0 para poder eliminarla
        branch1.removeAccountByCBU(client1, 123456789);
        assertTrue(bank.removeBranch(branch1));
    }
}