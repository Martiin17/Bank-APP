Feature: Transfer money with alias
 
  Scenario: Successfully transfer money
    Given A client with an account with CBU 1234, alias "hellow12" and a balance of 1000.0 and a second client with an account CBU 5678, alias "bye14" and balance of 1000.0
    When I transfer 200.0 to the alias "bye14"
    Then The first account balance should be 800.0 and the second account balance should be 1200.0

  Scenario: Cannot transfer a negative amount
    Given A client with an account with CBU 1234, alias "hellow12" and a balance of 1000.0 and a second client with an account CBU 5678, alias "bye14" and balance of 1000.0
    When I try to transfer -100.0 to the alias "bye14"
    Then The operation should be denied due to negative amount
    And The first account balance should remain 1000.0 and the second account balance should remain 1000.0

  Scenario: Cannot transfer more money than available balance
    Given A client with an account with CBU 1234, alias "hellow12" and a balance of 1000.0 and a second client with an account CBU 5678, alias "bye14" and balance of 1000.0
    When I try to transfer 1100.0 to the alias "bye14"
    Then The operation should be denied due to insufficient founds
    And The first account balance should remain 1000.0 and the second account balance should remain 1000.0

  Scenario: Cannot transfer to inexistent alias
    Given A client with an account with CBU 1234, alias "hellow12" and a balance of 1000.0 and a second client with an account CBU 5678, alias "bye14" and balance of 1000.0
    When I try to transfer 200.0 to the alias "fail15"
    Then The operation should be denied due to inexistent alias
    And The account balance should remain 1000.0