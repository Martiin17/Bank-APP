Feature: Depositing money into another account
 
  Scenario: Successfully deposit money into another account
    Given An account with CBU 1234 and a balance of 1000.0 and a second account with CBU 5678 and balance of 1000.0
    When I deposit 200.0 into the second account
    Then The first account balance should be 800.0 and the second account balance should be 1200.0

  Scenario: Cannot deposit a negative amount into another account
    Given An account with CBU 1234 and a balance of 1000.0 and a second account with CBU 5678 and balance of 1000.0 
    When I try to deposit -100.0 into the second account
    Then The operation should be denied
    And The first account balance should remain 1000.0 and the second account balance should remain 1000.0

  Scenario: Cannot deposit more money than available balance into another account
    Given An account with CBU 1234 and a balance of 1000.0 and a second account with CBU 5678 and balance of 1000.0 
    When I try to deposit 1100.0 into the second account
    Then The operation should be denied due to insufficient funds
    And The first account balance should remain 1000.0 and the second account balance should remain 1000.0