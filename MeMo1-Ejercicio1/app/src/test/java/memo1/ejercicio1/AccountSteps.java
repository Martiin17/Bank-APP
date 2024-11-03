package memo1.ejercicio1;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class AccountSteps {
    private Account account1;
    private Account account2;
    private boolean operationResult;
    private Branch branch1;
    private Bank bank;
    private Client client1;
    private Client client2;
    private Client testClient;
    private Branch branchTest;
    private Branch branchFound;
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


    @Given("An account with CBU {long} and alias {string}")
    public void createAccountWithDefaultBalance(long CBU, String alias) {
        client1.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), CBU, alias);
        account1 = bank.getAccountByCBU(CBU);
    }


    @Given("An account with CBU {long}, alias {string} and a balance of {double}")
    public void createAccountWithtBalance(long CBU, String alias, double balance) {
        client1.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), CBU, balance, alias);
        account1 = bank.getAccountByCBU(CBU);
    }


    @Given("An account with CBU {long}, alias {string} radicated on the branch with name branchNumber: {int}, name: {string} and direction: {string}")
    public void createBranchAndAccount(long CBU, String alias, int branchNumber, String name, String direction) {
        branchTest = bank.createBranch(branchNumber, direction, name);
        client1.createAccountAsOwner(bank,  bank.getBranch(branchTest.getBranchNumber()), CBU, alias);
        account1 = bank.getAccountByCBU(CBU);
    }


    @When("I try to create another account with the same CBU {long}, diferent alias {string} and a balance of {double}")
    public void tryToCreateAnotherAccountWithTheSameCBU(long repeatCBU, String alias, double balance) {
        try{
            client1.createAccountAsOwner(bank, branch1, repeatCBU, balance, alias);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @When("I try to create another account with the same alias {string}, diferent CBU {long} and a balance of {double}")
    public void tryToCreateAnotherAccountWithTheSameAlias(String repeatAlias, long CBU, double balance) {
        try{
            client1.createAccountAsOwner(bank, branch1, CBU, balance, repeatAlias);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @When("I change alias to {string}")
    public void changeAlias(String newAlias) {
        account1.setAlias(newAlias);
    }

    @When("I remove the account with CBU {long}")
    public void removeExistentAccount(long CBU) {
        branch1.removeAccountByCBU(client1, CBU);
    }

    @When("I try to remove inexistent account with CBU {long}")
    public void tryToRemoveInexistentAccount(long CBU) {
        try{
            branch1.removeAccountByCBU(client1, CBU);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @When("I look for the account branch with CBU {long}")
    public void tryLookAccountBranchWithCBU(long CBU) {
        try{
            branchFound = bank.searchAccountBranchWithCbu(CBU);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
            operationResult = false;
        }
    }

    @When("I look for the account branch with alias {string}")
    public void tryLookAccountBranchWithAlias(String alias) {
        try{
            branchFound = bank.searchAccountBranchWithAlias(alias);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
            operationResult = false;
        }
    }

    @When("I try to create an account without a branch")
    public void tryToCreateAnAccountWithoutBranch() {
        try{
            client1.createAccountAsOwner(bank, null, 786955345L, "Fail15");
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @When("I try to create an account without an owner")
    public void tryToCreateAnAccountWithoutAnOwner() {
        try{
            account2 = new Account(bank, null, 786955345L, "Fail15");
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @Then("The operation should be denied due to repeat CBU")
    public void verifyRepeatCBU() {
        assertNotNull(this.iae);
    }

    @Then("The operation should be denied due to repeat alias")
    public void verifyRepeatAlias() {
        assertNotNull(this.iae);
    }

    @Then("The operation should be denied due to inexistent account")
    public void verifyInexistentAccount() {
        assertNotNull(this.iae);
    }

    @Then("The account with CBU {long} should be have a balance of {double}")
    public void verifyAccountBalance(long CBU, Double expectedBalance) {
        assertEquals(expectedBalance, account1.getBalance(), 0.01);
    }

    @Then("The alias account should be {string}")
    public void verifyNewAlias(String newAlias) {
        assertEquals(newAlias, account1.getAlias());
    }

    @Then("The account with CBU {long} should be dont exist")
    public void verifyRemoveAccount(long CBU) {
        assertEquals(null, bank.getAccountByCBU(CBU));
    }

    @Then("I get the branchNumber: {int}, name: {string}, direction: {string}")
    public void verifyFoundBranch(int branchNumber, String name, String direction) {
        assertEquals(branchNumber, branchFound.getBranchNumber());
        assertEquals(name, branchFound.getName());
        assertEquals(direction, branchFound.getDirection());
    }


    /*@Then("The operation should be denied due to ilegal argument")
    public void verifyIlegalArgument() {
        assertNotNull(this.iae);
    }*/

    @Then("The operation should be denied due to inexistent aliass")
    public void verifyInexistentAlias() {
        assertNotNull(this.iae);
    }

    @Then("The operation should be denied due to inexistent CBU")
    public void verifyInexistentCBU() {
        assertNotNull(this.iae);
    }

    @Then("The operation should be denied due to branch can not be null")
    public void verifyCantCreateAccountWithoutBranch() {
        assertNotNull(this.iae);
    }

    @Then("The operation should be denied due to owner can not be null")
    public void verifyCantCreateAccountWithoutAnOwner() {
        assertNotNull(this.iae);
    }
} 
