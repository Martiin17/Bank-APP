package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

// Pruebas unitarias

class ClientTest {
    private Client client1;
    private Client client2;
    private Client testClient;
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
    void createClient() {
        bank.createClient(146816, "John", "Duran", "3 Avenue", 10051098);
        testClient = bank.getClient(146816);
        assertEquals(146816, testClient.getDNI());
        assertEquals("John", testClient.getName());
        assertEquals("Duran", testClient.getSurname());
        assertEquals("3 Avenue", testClient.getDirection());
        assertEquals(10051098, testClient.getBornDate());
    }

    @Test
    void removeClient() {
        bank.createClient(146816, "John", "Duran", "3 Avenue", 10051098);
        bank.removeClient(146816);
        assertEquals(null, bank.getClient(146816));
    }

    @Test
    void cantRemoveClientWithAccountsShouldThrowException() {
        bank.createClient(146816, "John", "Duran", "3 Avenue", 10051098);
        testClient = bank.getClient(146816);
        testClient.createAccountAsOwner(bank, branch1, 5641065460546L, "Try190");
        assertThrows(IllegalArgumentException.class, () -> bank.removeClient(146816));
    }

    @Test
    void modificateClientDirection() {
        client1.setDirection("New avenue 15");
        assertEquals("New avenue 15", client1.getDirection());
    }

    @Test
    void modificateClientName() {
        client1.setName("Williams");
        assertEquals("Williams", client1.getName());
    }

    @Test
    void modificateClientSurname() {
        client1.setSurname("Albon");
        assertEquals("Albon", client1.getSurname());
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
    void searchMarriageDateReturnTheMarriageDate() {
        bank.setMarriageDate(client1,client2,02112024);
        assertEquals(02112024,  bank.getMarriageDate(client1.getDNI()));
    }

    @Test
    void searchMarriageDateWithInexistentDNIShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.getMarriageDate(9999L));
    }

    @Test
    void searchMarriageDateWithASinglePersonShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.getMarriageDate(client1.getDNI()));
    }

    @Test
    void setMarriageDateBetweenTwoClients() {
        bank.setMarriageDate(client1, client2, 19112021);
        bank.getMarriageDate(client2.getDNI());
    }

    @Test
    void setMarriageDateBetweenAClientAndNobodyThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.setMarriageDate(client1, null, 19112021));
    }

    @Test
    void setMarriageDateBetweenNobodyAndNobodyThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bank.setMarriageDate(null, null, 19112021));
    }

}