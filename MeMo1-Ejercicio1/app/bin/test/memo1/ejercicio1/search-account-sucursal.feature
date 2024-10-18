Feature: Search where an account is located in a sucursal

  Scenario: Successfully find the branch
    Given An account with CBU 123456789 and radicate on the sucursal1
    When I look for the account branch
    Then I find the sucursal1