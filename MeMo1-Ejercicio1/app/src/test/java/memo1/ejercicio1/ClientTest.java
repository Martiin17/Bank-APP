package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

// Pruebas unitarias

class ClientTest {
    private Client client1;
    private Client client2;
    private Branch branch1;
    private Bank bank;
    private Account account1;
    //private Account account2;

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
        //client2.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), 987654321L, 1000.0, "bye14");
        account1 = bank.getAccountByCBU(123456789);
        //account2 = bank.getAccountByCBU(987654321L);
    }

    @Test
    void createAccountWithRepeatCBUShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> client1.createAccountAsOwner(bank, branch1, 123456789L, "goodmorning14"));
    }

    @Test
    void createAccountWithRepeatAliasShoulThrowException() {
        assertThrows(IllegalArgumentException.class, () ->client1.createAccountAsOwner(bank, branch1, 999999999L, "hellow12"));
    }

    @Test
    void correctlyAddAccountAsCoOwner() {
       client2.joinAsCoOwner(account1);
       assertEquals(1,client2.getCoOwnerAccounts().size());
    }

    @Test
    void searchForMarriageDateWithInexistentDNIShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.searchMarrigeDate(9999));
    }

    @Test
    void searchForMarriageDateWithASinglePersonShouldReturnZero() {
        assertEquals(0, bank.searchMarrigeDate(client1.getDNI()));
    }
}