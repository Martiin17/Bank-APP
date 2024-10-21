Feature: Search where an account is located in a sucursal

  Scenario: Successfully find the branch
    Given An account with CBU 123456789 and radicate on the sucursal1
    When I look for the account branch
    Then I find the sucursal1

  Scenario: Cant find the branch with cbu
    Given A dont exist CBU 123456789
    When I look for the account branch
    Then The operation should be denied due to inexistent cbu

  Scenario: Cant find the branch with alias
    Given A dont exist alias hellow12
    When I look for the account branch
    Then The operation should be denied due to inexistent alias