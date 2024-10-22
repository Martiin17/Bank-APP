package memo1.ejercicio1;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;

import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class AccountSteps {
    //private Account account0;
    private Account account1;
    private Account account2;
    private boolean operationResult;
    private Branch branch1;
    private Bank bank;
    private Client client1;
    private Client client2;
    private IllegalArgumentException iae;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.createBranch(1, "Street 15", "branch1");
        //bank.createClient(12345, "Math", "Johnson",  "Street 14", 19900413);
        //bank.createClient(56789, "Kamala", "Harrison", "Street 14", 19911013);
        //branch1 = bank.getBranch(1);
        //client1 = bank.getClient(12345);
        //client2 = bank.getClient(56789);
        //client1.createAccountAsTitular(bank, bank.getBranch(branch1.getBranchNumber()), 111111111L, "test0");
        //client1.createAccountAsTitular(bank, bank.getBranch(branch1.getBranchNumber()), 123456789L, 1000.0, "hellow12");
        //client2.createAccountAsTitular(bank, bank.getBranch(branch1.getBranchNumber()), 987654321L, 1000.0, "bye14");
        //account1 = bank.getAccountByCBU(123456789);
        //account2 = bank.getAccountByCBU(987654321L);
    }

    @Given("A client with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long}")
    public void createClient(long DNI,String name, String surname, String direction, long bornDate) {
        bank = new Bank();
        bank.createBranch(1, "Street 15", "branch1");
        bank.createClient(12345, "Math", "Johnson",  "Street 14", 19900413);
        client1 = bank.getClient(12345);
    }

    @When("I try to create another client with DNI {long}")
    public void tryToCreateAccountWithRepeatDNI(long repeatDNI) {
        try{
            bank.createClient(12345, "Harry", "Simmons",  "Street 21", 19930524);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
    }

    @Then("The client should be create with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long}")
    public void verifyAccountDates(long DNI,String name, String surname, String direction, long bornDate) {
        assertEquals(DNI, client1.getDNI());
        assertEquals(name, client1.getName());
        assertEquals(surname, client1.getSurname());
        assertEquals(direction, client1.getDirection());
        assertEquals(bornDate, client1.getBornDate());
    }

    @Then("The operation should be denied due to repeat DNI")
    public void verifyRepeatDNI() {
        assertNotNull(this.iae);
    }


    /*SI @Given("I create an account with CBU {long} and alias {string}")
    public void createAccountWithDefaultBalance(long cbu, String alias) {
        client1.createAccountAsTitular(bank, bank.getBranch(branch1.getBranchNumber()), cbu, alias);
    }*/


    /*SI @Given("I create an account with CBU {long}, alias {string} and a balance of {double}")
    public void createAccountWithInitialBalance(long cbu, String alias, double balance) {
        client1.createAccountAsTitular(bank, bank.getBranch(branch1.getBranchNumber()), cbu, balance, alias);
    }*/

    /*@Given("An account with CBU {long} and a balance of {double}")
    public void anAccountWithCBUAndBalance(long cbu, double balance) {
        account = new Account(cbu, balance);
    }*/

    /*@Given("An account with CBU {long} and a balance of {double} and a second account with CBU {long} and balance of {double}")
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
    }*/

    /*@Given("An account with CBU {int} and a balance of {double} and a inexistent cbu {long}")
    public void createAccountsWithCBUAndBalance(long cbu, Double balance, long inexistentCbu){
        account = new Account(cbu, balance);
        sucursal1 = new Sucursal(1, "PrimeraJunta  829", "sucursal1");
        bank = new Bank();
        bank.addSucursal(sucursal1);
        sucursal1.addAccount(account);
        account.setBank(bank);
    }*/

    /* SI @When("I try to create another account with the same CBU {long} and a balance of {double}")
    public void depositIntoAccount(double balance, long cbu) {
        try{
            client1.createAccountAsTitular(bank, branch1, cbu, balance, "Dont12");
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
       
    }

    @When("I try to create another account with the same alias {string} and a balance of {double}")
    public void depositIntoAccount(double balance,String alias) {
        try{
            client1.createAccountAsTitular(bank, branch1, 231321314L, balance,alias);
        }catch(IllegalArgumentException iae){
            this.iae = iae;
        }
       
    } */

   /*@When("I deposit {double} into the account")
    public void depositIntoAccount(double amount) {
        operationResult = account.deposit(amount);
    }*/

    /*@When("I try to deposit {double} into the account")
    public void tryToDepositIntoAccount(double amount) {
        operationResult = account.deposit(amount);
    }*/

    /*@When("I withdraw {double} from the account")
    public void withdrawFromAccount(double amount) {
        operationResult = account.withdraw(amount);
    }*/

    /*@When("I try to withdraw {double} from the account")
    public void tryToWithdrawFromAccount(double amount) {
        operationResult = account.withdraw(amount);
    }*/

    /*@When("I transfer {double} into the second account")
    public void depositToSecondAccount(Double amount) {
        account.transferWithCBU(amount, account2.getCbu());
    }*/

   /*@When("I try to transfer {double} into the second account")
    public void tryToDepositIntoSecondAccount(Double amount) {
        account.transferWithCBU(amount, account2.getCbu());
    }*/

    /*@When("I try to transfer {double} to the cbu {long}")
    public void tryToTransferToInexistentCBU(Double amount, long inexistentCbu) {
        operationResult = account.transferWithCBU(amount, inexistentCbu);;
    }*/

    /*Si @Then("The operation should be denied due to repeat CBU")
    public void verifyRepeatCBU() {
        assertNotNull(iae);
    }

    @Then("The operation should be denied due to repeat alias")
    public void verifyRepeatAlias() {
        assertNotNull(iae);
    }

    @Then("The account with CBU {long} should be have a balance of {double}")
    public void verifyAccountBalance(long cbu, double expectedBalance) {
        Account account = client1.getOwnerAccountWithCBU(cbu);
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }*/

    /*@Then("The operation should be denied due to negative amount")
    public void verifyOperationDenied() {
        if(!operationResult) {
            System.out.println("negative amount");
        }
        assertFalse(operationResult);
    }*/

    /*@Then("The account balance should remain {double}")
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
    }*/

} 
