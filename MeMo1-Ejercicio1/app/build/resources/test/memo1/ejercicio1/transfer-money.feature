Feature: Transfer money
 
  Scenario: Successfully transfer money
    Given An account with CBU 1234 and a balance of 1000.0 and a second account with CBU 5678 and balance of 1000.0
    When I transfer 200.0 into the second account
    Then The first account balance should be 800.0 and the second account balance should be 1200.0

  Scenario: Cannot transfer a negative amount
    Given An account with CBU 1234 and a balance of 1000.0 and a second account with CBU 5678 and balance of 1000.0 
    When I try to transfer -100.0 into the second account
    Then The operation should be denied due to negative amount
    And The first account balance should remain 1000.0 and the second account balance should remain 1000.0

  Scenario: Cannot transfer more money than available balance
    Given An account with CBU 1234 and a balance of 1000.0 and a second account with CBU 5678 and balance of 1000.0 
    When I try to transfer 1100.0 into the second account
    Then The operation should be denied due to insufficient founds
    And The first account balance should remain 1000.0 and the second account balance should remain 1000.0