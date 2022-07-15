Feature: Get a Skateboard(s)

  Background:
    * url baseUrl
    * def name = 'toy machine'
    * def width = 7.75
    * def length = 31.75
    * def createdSkateboard = call read('classpath:/karate/skateboard/reusable/createSkateboard.feature') { name: '#(name)', width: #(width), length: #(length)}
    * print createdSkateboard

  Scenario: Get a Skateboard
    Given path 'skateboard' , createdSkateboard.response.id
    And header Accept = 'application/json'
    When method get
    Then print responseStatus,response
    And assert responseStatus == 200
    And match response.name == name
    And match response.width == width
    And match response.length == length