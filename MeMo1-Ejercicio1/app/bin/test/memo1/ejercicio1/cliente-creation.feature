Feature: Client creation

  Scenario: Successfully create a client
    Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413 without accounts
    Then The client should be create with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413

  Scenario: Cant create a client with repeat DNI
    Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413 without accounts
    When I try to create another client with DNI 12345
    Then The operation should be denied due to repeat DNI