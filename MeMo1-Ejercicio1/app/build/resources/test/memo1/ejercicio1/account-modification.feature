Feature: Account dates modification

    Scenario: Successfully dates modification
        Given An account with CBU 123456789, alias "hellow12" and a balance of 1000.0
        When I change alias to "bye14"
        Then The alias account should be "bye14"
    