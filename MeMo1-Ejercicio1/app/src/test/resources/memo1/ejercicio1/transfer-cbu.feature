Feature: Transfer money with CBU
 
  Scenario: Successfully transfer money with CBU
    Given A client with an account with CBU 1234, alias "hellow12" and a balance of 1000.0 and a second client with an account CBU 5678, alias "bye14" and balance of 1000.0
    When I transfer 200.0 to the CBU 5678
    Then The first account balance should be 800.0 and the second account balance should be 1200.0

  Scenario: Cannot transfer a negative amount with CBU
    Given A client with an account with CBU 1234, alias "hellow12" and a balance of 1000.0 and a second client with an account CBU 5678, alias "bye14" and balance of 1000.0
    When I try to transfer -100.0 to the CBU 5678
    Then The operation should be denied due to negative amount
    And The first account balance should remain 1000.0 and the second account balance should remain 1000.0

  Scenario: Cannot transfer more money than available balance with CBU
    Given A client with an account with CBU 1234, alias "hellow12" and a balance of 1000.0 and a second client with an account CBU 5678, alias "bye14" and balance of 1000.0
    When I try to transfer 1100.0 to the CBU 5678
    Then The operation should be denied due to insufficient founds
    And The first account balance should remain 1000.0 and the second account balance should remain 1000.0

  Scenario: Cannot transfer to inexistent CBU
    Given A client with an account with CBU 1234, alias "hellow12" and a balance of 1000.0 and a second client with an account CBU 5678, alias "bye14" and balance of 1000.0
    When I try to transfer 200.0 to the CBU 9999
    Then The operation should be denied due to inexistent cbu
    And The first account balance should remain 1000.0 and the second account balance should remain 1000.0