package memo1.ejercicio1;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class BranchSteps {
    //private Account account1;
    //private Account account2;
    //private boolean operationResult;
    private Branch branch1;
    private Bank bank;
    private Client client1;
    private Client client2;
    //private Client testClient;
    private Branch branchTest;
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

    @Given("A branch with branchNumber: {int}, name: {string}, direction: {string}")
    public void createBranch(int branchNumber, String name, String direction) {
        branchTest = bank.createBranch(branchNumber, direction, name);
    }

    @Given("A branch with branchNumber: {int}, name: {string}, direction: {string} without accounts")
    public void createBranchWithoutAccounts(int branchNumber, String name, String direction) {
        branchTest = bank.createBranch(branchNumber, direction, name);
    }

    @Given("A branch with branchNumber: {int}, name: {string}, direction: {string} and an account")
    public void createBranchWithAccounts(int branchNumber, String name, String direction) {
        branchTest = bank.createBranch(branchNumber, direction, name);
        client1.createAccountAsOwner(bank, branchTest, 6464646848328L, "extraAccount129");
    }

    @When("I try to create another branch with branchNumber: {int}, name: {string}, direction: {string}")
    public void tryToCreateRepeatBranchNumber(int repeatBranchNumber, String name, String direction) {
        try{
            bank.createBranch(repeatBranchNumber, name, direction);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @When("I modificate branch name to {string}")
    public void modifcateBranchName(String newBranchName) {
        branchTest.setName(newBranchName);
    }

    @When("I modificate branch direction to {string}")
    public void modifcateBranchDirection(String newBranchDirection) {
        branchTest.setDirection(newBranchDirection);
    }

    @When("I remove the branch")
    public void removeBranchWithOutAccounts() {
        bank.removeBranch(branchTest);
    }

    @When("I try to remove the branch")
    public void tryToRemoveBranchWithAccounts() {
        try{
            bank.removeBranch(branchTest);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @Then("The branchNumber should be {int}, the name should be {string} and the direction should de {string}")
    public void verifyBranchCreation(int branchNumber, String name, String direction) {
        assertEquals(branchNumber, branchTest.getBranchNumber());
        assertEquals(name, branchTest.getName());
        assertEquals(direction, branchTest.getDirection());
    }

    @Then("The branch name should be {string}")
    public void verifyNewBranchName(String newBranchName) {
        assertEquals(newBranchName, branchTest.getName());
    }

    @Then("The branch direction should be {string}")
    public void verifyNewBranchDirection(String newBranchDirection) {
        assertEquals(newBranchDirection, branchTest.getDirection());
    }

    @Then("The branch should be dont exist")
    public void verifyBranchDontExist() {
        assertEquals(null, bank.getBranch(branchTest.getBranchNumber()));
    }

    @Then("The operation should be denied due to this branch have accounts")
    public void verifyCantRemoveBranchWithAccounts() {
        assertNotNull(this.iae);
    }

    @Then("The operation should be denied due to repeat branchNumber")
    public void verifyCantCreateTwoBranchesWithTheSameBranchNumber() {
        assertNotNull(this.iae);
    }

}