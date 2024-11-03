Feature: Branch creation
  Scenario: Successfully create a branch
    Given A branch with branchNumber: 120, name: "branch120", direction: "Street 16"
    Then The branchNumber should be 120, the name should be "branch120" and the direction should de "Street 16"

  Scenario: Cant create a branch with repeat branchNumber
    Given A branch with branchNumber: 120, name: "branch120", direction: "Street 16"
    When I try to create another branch with branchNumber: 120, name: "branch222", direction: "Street 21"
    Then The operation should be denied due to repeat branchNumber


