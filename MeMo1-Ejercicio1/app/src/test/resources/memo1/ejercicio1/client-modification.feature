Feature: Client dates modification

    Scenario: Successfully client direction modification
        Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413
        When I modificate direction to "Street 15"
        Then The direction should be "Street 15"
    
    Scenario: Successfully client name modification
        Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413
        When I modificate name to "Josh"
        Then The name should be "Josh"

    Scenario: Successfully client surname modification
        Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413
        When I modificate surname to "Garcia"
        Then The surname should be "Garcia"

    #Scenario: Cant modificate client born date
        #Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413
        #When I try to modificate born date to "20241018"
        #Then The born date should be "19900413"
        #And The operation should be denied due to cant modificate this date

    #Scenario: Cant modificate client DNI
        #Given A client with DNI: 12345, name: "Math", surname: "Johnson", direction: "Street 14" and born date: 19900413
        #When I try to modificate DNI to 12346
        #Then The DNI should be 12345
        #And The operation should be denied due to cant modificate this date
    