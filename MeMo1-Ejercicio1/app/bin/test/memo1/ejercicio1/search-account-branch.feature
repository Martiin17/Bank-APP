Feature: Search where an account is located in a branch

  Scenario: Successfully find the branch with CBU
    Given An account with CBU 123456789, alias "hellow12" radicated on the branch with name branchNumber: 120, name: "branchTest" and direction: "Street 15"
    When I look for the account branch with CBU 123456789
    Then I get the branchNumber: 120, name: "branchTest", direction: "Street 15"

  Scenario: Successfully find the branch with alias
    Given An account with CBU 123456789, alias "hellow12" radicated on the branch with name branchNumber: 120, name: "branchTest" and direction: "Street 15"
    When I look for the account branch with alias "hellow12"
    Then I get the branchNumber: 120, name: "branchTest", direction: "Street 15"

  Scenario: Cant find the branch for inexistent cbu
    Given An account with CBU 123456789, alias "hellow12" radicated on the branch with name branchNumber: 120, name: "branchTest" and direction: "Street 15"
    When I look for the account branch with CBU 19029393920
    Then The operation should be denied due to inexistent cbu

  Scenario: Cant find the branch for inexistent alias
    Given An account with CBU 123456789, alias "hellow12" radicated on the branch with name branchNumber: 120, name: "branchTest" and direction: "Street 15"
    When I look for the account branch  with alias "dontExist199"
    Then The operation should be denied due to inexistent alias