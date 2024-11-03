Feature: Set marriage date
  Scenario: Successfully set marrige date
    Given A client with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long}
    And Another client with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long}
    When I set the marriage date between them on 03112024
    Then The marrige date for this clients should be 03112024

  Scenario: Cant set a marriage date for nobody
    When I try to set the marriage date between nobody and nobody on 03112024
    Then The operation should be denied to cannot set marrige date for nobody

  Scenario: Cant set a marriage date for one client
    Given A client with DNI: {long}, name: {string}, surname: {string}, direction: {string} and born date: {long}
    When I try to set the marriage date between the client and nobody on 03112024
    Then The operation should be denied to cannot set marrige date for a person with nobody

    #Asumo que una persona puede estar casado con mas de una


