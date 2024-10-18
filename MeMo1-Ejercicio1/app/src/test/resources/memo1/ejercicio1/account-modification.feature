Feature: Account dates modification

    Scenario: Successfully dates modification
        Given An account with alias hellow12 and a balance of 500.0
        When I change alias to bye14
        Then The alias account should be bye14
    