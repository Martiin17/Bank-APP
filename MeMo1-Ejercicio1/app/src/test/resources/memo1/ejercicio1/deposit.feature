Feature: Depositing money
 
  Scenario: Successfully deposit money into an account
    Given Account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    When I deposit 200.0 into the account
    Then The account balance should be 1200.0
    And A register should be created

  Scenario: Cannot deposit a negative amount
    Given Account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    When I try to deposit -100.0 into the account
    Then The operation should be denied due to negative amount
    And The account balance should remain 1000.0

  Scenario: Cannot deposit into a not owner or co-owner account
    Given Account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    When I try to deposit 100.0 into no owner or co-owner account
    Then The operation should be denied due to not owner or co-owner account

  #Scenario: Cannot deposit into a inexistent account
    #Given Account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    #When I try to deposit 100.0 into inexistent account
    #Then The operation should be denied due to inexistent account
   

  