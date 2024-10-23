Feature: Account creation
  Scenario: Successfully create an account with default balance
    Given An account with CBU 123456789 and alias "hellow12"
    Then The account with CBU 123456789 should be have a balance of 0.0

  Scenario: Successfully create an account with an initial balance
    Given An account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    Then The account with CBU 123456789 should be have a balance of 1000.0

  Scenario: Cant create an account with a repeat CBU
    Given An account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    When I try to create another account with the same CBU 123456789, diferent alias "goodevening15" and a balance of 1000.0
    Then The operation should be denied due to repeat CBU

  Scenario: Cant create an account with a repeat alias
    Given An account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    When I try to create another account with the same alias "hellow12", diferent CBU 55555555 and a balance of 1000.0
    Then The operation should be denied due to repeat alias
