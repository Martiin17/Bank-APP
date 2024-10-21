Feature: Account creation
  Scenario: Successfully create an account with default balance
    Given I create an account with a owner, CBU 123456789 and alias hellow12
    Then The account balance should be 0.0

  Scenario: Successfully create an account with an initial balance
    Given I create an account with a owner, CBU 123456789, alias hellow12 and a balance of 1000.0
    Then The account balance should be 1000.0

  Scenario: Cant create an account with a repeat CBU
    Given I create an account with a owner, CBU 123456789, alias hellow12 and a balance of 1000.0
    When I try to create another account with the same owner, CBU 123456789 and a balance of 1000.0
    Then The first account should be create with a balance of 1000.0
    And The operation should be denied due to repeat CBU

  Scenario: Cant create an account with a repeat alias
    Given I create an account with a owner, CBU 123456789, alias hellow12 and a balance of 1000.0
    When I try to create another account with the same owner alias hellow12 and a balance of 1000.0
    Then The first account should be create with a balance of 1000.0
    And The operation should be denied due to repeat alias
