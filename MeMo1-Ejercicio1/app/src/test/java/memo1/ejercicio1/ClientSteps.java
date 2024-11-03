package memo1.ejercicio1;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class ClientSteps {
    private Account account1;
    //private Account account2;
    //private boolean operationResult;
    private Branch branch1;
    private Bank bank;
    private Client client1;
    private Client client2;
    private Client testClient;
    private MarriageRegister testMarriageRegister;
    //private Branch branchTest;
    //private Branch branchFound;
    private IllegalArgumentException iae;

    @Before
    public void setUp() {
        bank = new Bank();
        bank.createBranch(1, "Street 15", "branch1");
        bank.createClient(19999, "Math", "Johnson",  "Street 14", 19900413);
        bank.createClient(20000, "Kamala", "Harrison", "Street 14", 19911013);
        branch1 = bank.getBranch(1);
        client1 = bank.getClient(19999);
        client2 = bank.getClient(20000);
    }

    @Given("A client with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long} without accounts")
    public void createClientWithoutAccounts(long DNI,String name, String surname, String direction, long bornDate) {
        bank.createClient(DNI, name, surname,  direction, bornDate);
        testClient = bank.getClient(DNI);
    }

    @Given("A client with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long}")
    public void createClientsMarrige(long DNI1, String name1, String surname1, String direction1, long bornDate1){
        client1 = bank.createClient(DNI1, name1, surname1, direction1, bornDate1);
    }

    @Given("His wife who is client too with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long}. They have marrige on {long}")
    public void createClientsMarrige2(long DNI2, String name2, String surname2, String direction2, long bornDate2, long marriageDate){
        client2 = bank.createClient(DNI2, name2, surname2, direction2, bornDate2);
        bank.setMarriageDate(client1, client2, marriageDate);
    }

    @Given("A client with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long} with an account")
    public void createClientWithAnAccount(long DNI,String name, String surname, String direction, long bornDate) {
        bank.createClient(DNI, name, surname,  direction, bornDate);
        testClient = bank.getClient(DNI);
        testClient.createAccountAsOwner(bank, branch1, 0246504045L, "goodEvening152");
    }

    @When("I try to create another client with DNI {long}")
    public void tryToCreateAccountWithRepeatDNI(long repeatDNI) {
        try{
            bank.createClient(repeatDNI, "Harry", "Simmons",  "Street 21", 19930524);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @When("I modificate direction to {string}")
    public void modificateDirection(String newDirection) {
        client1.setDirection(newDirection);
    }

    @When("I modificate name to {string}")
    public void modificateName(String newName) {
        client1.setName(newName);
    }

    @When("I modificate surname to {string}")
    public void modificateSurname(String surname) {
        client1.setSurname(surname);
    }

    @When("I remove the client with DNI {long}")
    public void removeClientFromBankClientsList(long DNI) {
        bank.removeClient(DNI);
    }

    @When("I try to remove inexistent client with DNI {long}")
    public void tryRemoveInexistentClientFromBankClientsList(long DNI) {
        try{
            bank.removeClient(DNI);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @When("I search the marriage date")
    public void searchForTheMarrigeDate() {
        bank.getMarriageDate(client1.getDNI());
    }

    @When("I try to remove the client with DNI {long}")
    public void tryToRemoveClientWithAccounts(long DNI) {
        try{
            bank.removeClient(DNI);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @When("I try to search the marriage date for inexistent DNI {long}")
    public void tryToSearchMarriageDateForInexistentDNI(long DNI) {
        try{
            bank.getMarriageDate(DNI);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @When("I try to search the marriage date")
    public void tryToSearchMarriageDateForInexistentDNI() {
        try{
            bank.getMarriageDate(client1.getDNI());
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @Then("The client should be create with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long}")
    public void verifyAccountDates(long DNI,String name, String surname, String direction, long bornDate) {
        assertEquals(DNI, testClient.getDNI());
        assertEquals(name, testClient.getName());
        assertEquals(surname, testClient.getSurname());
        assertEquals(direction, testClient.getDirection());
        assertEquals(bornDate, testClient.getBornDate());
    }

    @Then("The operation should be denied due to repeat DNI")
    public void verifyRepeatDNI() {
        assertNotNull(this.iae);
    }

    @Then("The operation should be denied due to inexistent client")
    public void verifyInexistentClient() {
        assertNotNull(this.iae);
    }

    @Then("The client with DNI {long} should be dont exist")
    public void verifyRemoveClient(long DNI) {
        assertEquals(null, bank.getClient(DNI));
    }

    @Then("The direction should be {string}")
    public void verifyNewDirection(String newDirection) {
        assertEquals(newDirection, client1.getDirection());
    }

    @Then("The name should be {string}")
    public void verifyNewName(String newName) {
        assertEquals(newName, client1.getName());
    }

    @Then("The surname should be {string}")
    public void verifyNewSurname(String newSurname) {
        assertEquals(newSurname, client1.getSurname());
    }

    @Then("I get the marriage date on {long}")
    public void veifyMarrigeDateIsANumber(long marrigeDate) {
        assertEquals(marrigeDate, bank.getMarriageDate(client1.getDNI()));
    }

    @Then("The operation should be denied due to the client have accounts")
    public void veirfyDontRemoveAClietWithAccounts() {
        assertNotNull(this.iae);
    }

    @Then("The operation should be denied due to DNI is not exist")
    public void verifyInexistentDNI() {
        assertNotNull(this.iae);
    }

    @Then("The operation should be denied due to the client is not marriage")
    public void verifyClientIsNotMarriage() {
        assertNotNull(this.iae);
    }

}