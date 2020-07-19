Feature: Validating Space APIs
  @addPlace @Regression
  Scenario Outline: Verify if Place is being successfully added  using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then The API is sucessfully called with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

  Examples:
    |name|language|address|
    |dd  | cdvh   | hjjhvb|
  #  |dfg | fdgjk  | jhgj  |

  @deletePlace @Regression
  Scenario: Verify if Delete Place Functionality is working as expected
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    And "status" in response body is "OK"
