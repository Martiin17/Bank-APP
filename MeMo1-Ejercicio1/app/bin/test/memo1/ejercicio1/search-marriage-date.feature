Feature: Search if the cliente is married

  Scenario: Successfully found the client is married
    Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413
    And His wife who is client too with DNI: 56789, name: "Kamala", surname: "Harrison", direction: "Street 14" and born date: 19911013. They have marrige on 20241022
    When I search the marriage date
    Then I get the marriage date on 20241022

  Scenario: Cant found the marriage date for inexistent DNI
    When I try to search the marriage date for inexistent DNI 6468646
    Then The operation should be denied due to DNI is not exist

  Scenario: Cant found the marriage date for a client who is not marriage
    Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413
    When I try to search the marriage date
    Then The operation should be denied due to the client is not marriage