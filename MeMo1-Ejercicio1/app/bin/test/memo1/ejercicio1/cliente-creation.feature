Feature: Client creation

  Scenario: Successfully create a client
    Given I create a client with DNI, name, surname, direction and born date
    Then The client should be create with this data

  Scenario: Cant create a client with repeat DNI
    Given A client with DNI 123456
    When I try to create another client with DNI 123456
    Then The first client should be exist
    And The operation should be denied due to repeat DNI