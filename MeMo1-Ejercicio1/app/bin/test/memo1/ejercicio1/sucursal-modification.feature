Feature: Sucursal dates modification

    Scenario: Successfully name modification
        Given I create a sucursal with sucursalNumber: 1, name: sucursal1, direction: "Street 16"
        When I modificate name to sucursal2
        Then The name should be sucursal2

    Scenario: Successfully direction modification
        Given I create a sucursal with sucursalNumber: 1, name: sucursal1, direction: "Street 16"
        When I modificate direction to "Street 21"
        Then The direction should be "Street 21"

    Scenario: Cant modifcate sucursalNumber
        Given I create a sucursal with sucursalNumber: 1, name: sucursal1, direction: "Street 16"
        When I try to modificate sucursalNumber to 2
        Then The sucursalNumber should be 1
        and The operation should be denied due to cant modificate this date
    
    
  