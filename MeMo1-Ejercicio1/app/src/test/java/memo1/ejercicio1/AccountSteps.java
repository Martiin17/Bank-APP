package memo1.ejercicio1;

import static org.junit.Assert.*;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class AccountSteps {
    private Account account;
    private Account account2;
    private boolean operationResult;
    private Sucursal sucursal1;
    private Bank bank;
}

    /*@Given("I create an account with CBU {long}")
    public void createAccountWithDefaultBalance(long cbu) {
        account = new Account();
        account.setCbu(cbu);
    }

    @Given("I create an account with CBU {long} and a balance of {double}")
    public void createAccountWithInitialBalance(long cbu, double balance) {
        account = new Account(cbu, balance);
    }

    @Given("An account with CBU {long} and a balance of {double}")
    public void anAccountWithCBUAndBalance(long cbu, double balance) {
        account = new Account(cbu, balance);
    }

    @Given("An account with CBU {long} and a balance of {double} and a second account with CBU {long} and balance of {double}")
    public void createAccountsWithCBUAndBalance(long cbu, Double balance, long cbu2, Double balance2) {
        account = new Account(cbu, balance);
        account2 = new Account(cbu2, balance2);
        sucursal1 = new Sucursal(1, "PrimeraJunta  829", "sucursal1");
        bank = new Bank();
        bank.addSucursal(sucursal1);
        sucursal1.addAccount(account);
        sucursal1.addAccount(account2);
        account.setBank(bank);
        account2.setBank(bank);
    }

    @Given("An account with CBU {int} and a balance of {double} and a inexistent cbu {long}")
    public void createAccountsWithCBUAndBalance(long cbu, Double balance, long inexistentCbu){
        account = new Account(cbu, balance);
        sucursal1 = new Sucursal(1, "PrimeraJunta  829", "sucursal1");
        bank = new Bank();
        bank.addSucursal(sucursal1);
        sucursal1.addAccount(account);
        account.setBank(bank);
    }

    @When("I deposit {double} into the account")
    public void depositIntoAccount(double amount) {
        operationResult = account.deposit(amount);
    }

    @When("I try to deposit {double} into the account")
    public void tryToDepositIntoAccount(double amount) {
        operationResult = account.deposit(amount);
    }

    @When("I withdraw {double} from the account")
    public void withdrawFromAccount(double amount) {
        operationResult = account.withdraw(amount);
    }

    @When("I try to withdraw {double} from the account")
    public void tryToWithdrawFromAccount(double amount) {
        operationResult = account.withdraw(amount);
    }

    @When("I transfer {double} into the second account")
    public void depositToSecondAccount(Double amount) {
        account.transferWithCBU(amount, account2.getCbu());
    }

    @When("I try to transfer {double} into the second account")
    public void tryToDepositIntoSecondAccount(Double amount) {
        account.transferWithCBU(amount, account2.getCbu());
    }

    @When("I try to transfer {double} to the cbu {long}")
    public void tryToTransferToInexistentCBU(Double amount, long inexistentCbu) {
        operationResult = account.transferWithCBU(amount, inexistentCbu);;
    }

    @Then("The account balance should be {double}")
    public void verifyAccountBalance(double expectedBalance) {
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Then("The operation should be denied due to negative amount")
    public void verifyOperationDenied() {
        if(!operationResult) {
            System.out.println("negative amount");
        }
        assertFalse(operationResult);
    }

    @Then("The account balance should remain {double}")
    public void verifyBalanceRemains(double expectedBalance) {
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Then("The operation should be denied due to insufficient founds")
    public void verifyInsufficientFounds() {
         if(!operationResult) {
            System.out.println("insufficient founds");
        }
        assertFalse(operationResult);
    }


    @Then("The first account balance should be {double} and the second account balance should be {double}")
    public void verifyAccountsBalance(Double expectedBalance, Double expectedBalance2) {
        assertEquals(expectedBalance, account.getBalance(), 0.01);
        assertEquals(expectedBalance2, account2.getBalance(), 0.01);
    }

    @Then("The first account balance should remain {double} and the second account balance should remain {double}")
    public void VerifyAccountsBalance(Double expectedBalance, Double expectedBalance2) {
        assertEquals(expectedBalance, account.getBalance(), 0.01);
        assertEquals(expectedBalance2, account2.getBalance(), 0.01);
    }

   @Then("The operation should be denied due to inexistent cbu")
    public void verifyInexistentCBU() {
        if(!operationResult) {
            System.out.println(" inexistent cbu");
        }
        assertFalse(operationResult);
    }

    @Then("The first account balance should remain {double}")
    public void VerifyAccountBalance(Double expectedBalance) {
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

} */
