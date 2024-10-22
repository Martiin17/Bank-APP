Feature: Remove account

  Scenario: Successfully remove account
    Given An account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    When I remove the account with CBU 123456789
    Then The account with CBU 123456789 should be dont exist

  Scenario: I cant remove a inexistent account
    Given An account with CBU 123456789, alias "hellow12" and a balance of 1000.0
    When I try to remove inexistent account with CBU 111111
    Then The operation should be denied due to inexistent account