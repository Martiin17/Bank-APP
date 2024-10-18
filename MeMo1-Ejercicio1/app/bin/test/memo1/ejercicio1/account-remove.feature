Feature: Remove account

  Scenario: Successfully remove account
    Given An account with CBU 987654321 and a balance of 500.0
    When I remove the account
    Then The account should be dont exist

  Scenario: I cant remove a inexistent account
    Given An inexistent account with CBU 111111 and a balance of 500.0
    When I try to remove the account
    Then The operation should be denied due to inexistent account