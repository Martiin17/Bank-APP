Feature: Remove client

  Scenario: Successfully remove client
    Given A client with DNI: 12345, name: Math, surname: Johnson, direction: "Street 14" and born date: 19900413
    When I remove the client
    Then The client should be dont exist

  Scenario: I cant remove a inexistent client
    Given A inexistent client with DNI: 55555
    When I try to remove the client
    Then The operation should be denied due to inexistent client