Feature: Client creation

  Scenario: Successfully create a client
    GivenA client with DNI: 12345, name: Math, surname: Johnson, direction: "Street 14" and born date: "19900413"
    Then The client should be create with this data

  Scenario: Cant create a client with repeat DNI
    GivenA client with DNI: 12345, name: Math, surname: Johnson, direction: "Street 14" and born date: "19900413"
    When I try to create another client with DNI 123456
    Then The first client should be exist
    And The operation should be denied due to repeat DNI