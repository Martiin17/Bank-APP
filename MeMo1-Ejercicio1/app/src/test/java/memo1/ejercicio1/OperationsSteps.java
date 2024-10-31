package memo1.ejercicio1;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class OperationsSteps {
    private Account account1;
    private Account account2;
    private boolean operationResult;
    private Branch branch1;
    private Bank bank;
    private Client client1;
    private Client client2;
    //private Client testClient;
    //private Branch branchTest;
    //private Branch branchFound;
    //private IllegalArgumentException iae;

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

    @Given("A new account with CBU {long}, alias {string} and a balance of {double}")
    public void createAccountWithtBalance(long CBU, String alias, double balance) {
        client1.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), CBU, balance, alias);
        account1 = bank.getAccountByCBU(CBU);
    }

    @Given("A client with an account with CBU {long}, alias {string} and a balance of {double} and a second client with an account CBU {long}, alias {string} and balance of {double}")
    public void createTwoAccountsWithBalance(long CBU1, String alias1, double balance1, long CBU2, String alias2, double balance2) {
        client1.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), CBU1, balance1, alias1);
        account1 = bank.getAccountByCBU(CBU1);
        client2.createAccountAsOwner(bank, bank.getBranch(branch1.getBranchNumber()), CBU2, balance2, alias2);
        account2 = bank.getAccountByCBU(CBU2);
    }

    @When("I deposit {double} into the account")
    public void depositIntoAccount(double amount) {
        client1.deposit(account1, amount);
    }

    @When("I try to deposit {double} into the account")
    public void tryTodepositNegativeAmountIntoAccount(double amount) {
        client1.deposit(account1, amount);
    }

    @When("I try to deposit {double} into no owner or co-owner account")
    public void tryTodepositInNotOwnerOrCoOwnerAccount(double amount) {
        client2.createAccountAsOwner(bank, branch1, 50685046045L, "noOwnerAccount15");
        Account noOWnerAccount = bank.getAccountByCBU(50685046045L);
        operationResult = client1.deposit(noOWnerAccount, amount);
    }

    @When("I withdraw {double} from the account")
    public void withdrawMoneyFromAnAccount(double amount) {
        client1.withdraw(account1, amount);
    }

    @When("I try to withdraw {double} from the account")
    public void tryToWithdrawMoreMoneyThanAvaibableFromAnAccount(double amount) {
        operationResult = client1.withdraw(account1, amount);
    }

    @When("I try to withdraw {double} from not owner or co-owner account")
    public void tryToWithdrawMoneyFromNotOwnerOrCoOwnerAccount(double amount) {
        client2.createAccountAsOwner(bank, branch1, 50685046045L, "noOwnerAccount15");
        Account noOWnerAccount = bank.getAccountByCBU(50685046045L);
        operationResult = client1.withdraw(noOWnerAccount, amount);
    }

    @When("I transfer {double} to the CBU {long}")
    public void transferWithCBU(double balance, long CBU) {
        client1.trasnferWithCBU(account1, balance, CBU);
    }

    @When("I try to transfer {double} to the CBU {long}")
    public void tryToTransferWithCBU(double balance, long CBU) {
        operationResult = client1.trasnferWithCBU(account1, balance, CBU);
    }

    @When("I transfer {double} to the alias {string}")
    public void transferWithAlias(double balance, String alias) {
        client1.trasnferWithAlias(account1, balance, alias);
    }

    @When("I try to transfer {double} to the alias {string}")
    public void tryToTransferWithAlias(double balance, String alias) {
        operationResult = client1.trasnferWithAlias(account1, balance, alias);
    }

    @Then("The account balance should be {double}")
    public void verifyAccountBalance(double expectedBalance) {
        assertEquals(expectedBalance, account1.getBalance(), 0.01);
    }

    @Then("The account balance should remain {double}")
    public void verifyBalanceRemains(double expectedBalance) {
        assertEquals(expectedBalance, account1.getBalance(), 0.01);
    }

    @Then("The operation should be denied due to negative amount")
    public void verifyOperationDenied() {
        if(!operationResult) {
            System.out.println("negative amount");
        }
        assertFalse(operationResult);
    }

    @Then("The operation should be denied due to inexistent cbu")
    public void verifyInexistentCBU() {
        if(!operationResult) {
            System.out.println(" inexistent cbu");
        }
        assertFalse(operationResult);
    }

    @Then("The operation should be denied due to inexistent alias")
    public void verifyInexistentAlias() {
        if(!operationResult) {
            System.out.println("inexistent alias");
        }
        assertFalse(operationResult);
    }

    @Then("The first account balance should remain {double} and the second account balance should remain {double}")
    public void VerifyAccountsBalance(double expectedBalance, double expectedBalance2) {
        assertEquals(expectedBalance, account1.getBalance(), 0.01);
        assertEquals(expectedBalance2, account2.getBalance(), 0.01);
    }

    @Then("The operation should be denied due to not owner or co-owner account")
    public void verifyOperationDeniedBecauseNotOwnerOrCoOwnerAccount() {
        if(!operationResult) {
            System.out.println("no owner or co-owner account");
        }
        assertFalse(operationResult);
    }

    @Then("The operation should be denied due to insufficient founds")
    public void verifyInsufficientFounds() {
         if(!operationResult) {
            System.out.println("insufficient founds");
        }
        assertFalse(operationResult);
    }

    @Then("The first account balance should be {double} and the second account balance should be {double}")
    public void verifyExpectedBalances(double expectedBalance1, double expectedBalance2) {
        assertEquals(expectedBalance1, account1.getBalance(), 0.01);
        assertEquals(expectedBalance2, account2.getBalance(), 0.01);
    }

}