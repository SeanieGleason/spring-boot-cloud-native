@ignore @report=false
Feature: Reusable create a Skateboard.  Must provide:
  {name} - String
  {width} - double
  {length} - double.

  Background:
    * url baseUrl

  Scenario: Create a Skateboard

    Given path 'skateboard'
    And header Accept = 'application/json'
    And request
    """
    {
      "name": "#(name)",
      "width": "#(width)",
      "length": "#(length)"
    }
    """
    When method post
