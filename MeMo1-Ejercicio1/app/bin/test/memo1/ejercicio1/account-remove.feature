Feature: Remove account

  Scenario: Successfully remove account
    Given An account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    When I remove the account
    Then The account should be dont exist

  Scenario: I cant remove a inexistent account
    Given A inexistent account with CBU 111111
    When I try to remove the account
    Then The operation should be denied due to inexistent account