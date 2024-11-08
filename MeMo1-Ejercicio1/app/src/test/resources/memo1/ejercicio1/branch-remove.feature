Feature: Remove branch

  Scenario: Successfully remove branch
    Given A branch with branchNumber: 120, name: "branch120", direction: "Street 16" without accounts
    When I remove the branch
    Then The branch should be dont exist

  Scenario: I cant remove a branch with accounts
    Given A branch with branchNumber: 120, name: "branch120", direction: "Street 16" and an account
    When I try to remove the branch
    Then The operation should be denied due to this branch have accounts