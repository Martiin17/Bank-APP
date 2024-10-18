Feature: Remove sucursal

  Scenario: Successfully remove sucursal
    Given A sucursal with sucursalNumber: 1, name: sucursal1, direction: "Street 16" with out accounts
    When I remove the sucursal
    Then The sucursal should be dont exist

  Scenario: I cant remove a sucursal with accounts
    Given A sucursal with sucursalNumber: 1, name: sucursal1, direction: "Street 16" with accounts
    When I try to remove the sucursal
    Then The operation should be denied due to this sucursal have accounts