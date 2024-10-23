Feature: Sucursal dates modification

    Scenario: Successfully branch name modification
        Given I create a branch with branchNumber: 120, name: "branch120", direction: "Street 16"
        When I modificate branch name to "branch2"
        Then The branch name should be "branch2"

    Scenario: Successfully branch direction modification
        Given I create a branch with branchNumber: 120, name: "branch120", direction: "Street 16"
        When I modificate branch direction to "Street 21"
        Then The branch direction should be "Street 21"

    #Scenario: Cant modifcate branch sucursalNumber
        #Given I create a sucursal with sucursalNumber: 1, name: sucursal1, direction: "Street 16"
        #When I try to modificate sucursalNumber to 2
        #Then The sucursalNumber should be 1
        #And The operation should be denied due to cant modificate this date
    
    
  