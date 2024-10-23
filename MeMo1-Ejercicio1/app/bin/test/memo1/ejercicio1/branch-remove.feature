Feature: Remove branch

  Scenario: Successfully remove branch
    Given I create a branch with branchNumber: 120, name: "branch120", direction: "Street 16"
    When I remove the branch with out accounts
    Then The branch should be dont exist

  Scenario: I cant remove a branch with accounts
    Given I create a branch with branchNumber: 120, name: "branch120", direction: "Street 16"
    When I try to remove the branch with accounts
    Then The operation should be denied due to this branch have accounts