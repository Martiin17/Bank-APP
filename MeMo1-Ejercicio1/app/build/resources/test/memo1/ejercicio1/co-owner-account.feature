Feature: Join to account as co-owner

    Scenario: Successfully join as co-owner
        Given An account with CBU 123456789, alias "hellow12" and a balance of 1000.0
        When I join to the account as co-owner
        Then The account should be on my co-owner list

    Scenario: Cant join as co-owner if is my own account
        Given An account with CBU 123456789, alias "hellow12" and a balance of 1000.0
        When I try to join as co-owner for my own account
        Then The operation should be denied due to ilegal argument