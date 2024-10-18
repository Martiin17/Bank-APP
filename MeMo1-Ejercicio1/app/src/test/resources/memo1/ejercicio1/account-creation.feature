Feature: Account creation
  Scenario: Successfully create an account with default balance
    Given I create an account with CBU 123456789
    Then The account balance should be 0.0

  Scenario: Successfully create an account with an initial balance
    Given I create an account with CBU 987654321 and a balance of 500.0
    Then The account balance should be 500.0

  Scenario: I cant create an account with a repeat cbu
    Given I create an account with CBU 987654321 and a balance of 500.0 
    When I try to create another account with CBU 987654321 and a balance of 500.0
    Then The first account should be create with a balance of 500.0
    And the second account throw IllegalArgumentException

  Scenario: I cant create an account with a repeat alias
    Given I create an account with alias hellow12 and a balance of 500.0
    When I try to create another account with alias hellow12 and a balance of 500.0
    Then The first account should be create with a balance of 500.0
    And the second account throw IllegalArgumentException

