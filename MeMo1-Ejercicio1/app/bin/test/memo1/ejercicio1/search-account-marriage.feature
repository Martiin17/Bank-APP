Feature: Search if the cliente is married

  Scenario: Successfully found the client is married
    Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413 and his wife who is client too with DNI: 56789, name: "Kamala", surname: "Harrison", direction: "Street 14" and born date: 19911013. They have marrige on 20241022
    When I look for the fist account marriage 
    Then I get the wedding date 20241022

    #Scenario: Cant found the client is married
      #Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413
      #When I look if the client is marrige
      #Then The operation should be denied due to the client is not marriage