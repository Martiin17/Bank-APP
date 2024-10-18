Feature: Sucursal creation
  Scenario: Successfully create a sucursal
    Given I create a sucursal with sucursalNumber: 1, name: sucursal1, direction: "Street 16"
    Then The sucursalNumber should be 1, the name should be sucursal1 and the direction should de "Street 16"

  Scenario: Cant create a sucursal with repeat sucursalNumber
    Given I create a sucursal with sucursalNumber: 1, name: sucursal1, direction: "Street 16"
    When I try to create another sucursal with sucursalNumber: 1, name: sucursal2, direction: "Street 21"
    Then The first sucursal should be create with sucursalNumber 1, name sucursal1 and direction "Street 16"
    And The operation should be denied due to repeat sucursalNumber


