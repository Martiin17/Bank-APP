Feature: Account creation
  Scenario: Successfully create an account with default balance
    Given I create an account with CBU 111111111 and alias "test0"
    Then The account with CBU 111111111 should be have a balance of 0.0

  Scenario: Successfully create an account with an initial balance
    Given I create an account with CBU 123456789, alias hellow12 and a balance of 1000.0
    Then The account with CBU 123456789 should be have a balance of 1000.0

  Scenario: Cant create an account with a repeat CBU
    Given I create an account with CBU 123456789, alias hellow12 and a balance of 1000.0
    When I try to create another account with the same CBU 123456789 and a balance of 1000.0
    Then The operation should be denied due to repeat CBU

  Scenario: Cant create an account with a repeat alias
    Given I create an account with CBU 123456789, alias hellow12 and a balance of 1000.0
    When I try to create another account with the same alias hellow12 and a balance of 1000.0
    Then The operation should be denied due to repeat alias
