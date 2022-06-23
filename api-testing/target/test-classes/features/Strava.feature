@Strava @Tryout

Feature: Testing my Strava data


  Scenario Outline: Validating logged in user
    Given I have my Strava API access token
    When I retrieve the logged in athlete data
    Then my <firstName>,  <lastName> and <userId> are in the response with <statusCode>
    Examples:
      | userId  | firstName | lastName  | statusCode |
      | 1067810 | Axel      | Vuylsteke | 200        |

  Scenario Outline: Getting my strava stats
    Given I have my Strava API access token
    When I retrieve the <userId> stats
    Then I get the user with <userId> all-time stats with <statusCode>

    Examples:
      | userId   | statusCode |
      | 1067810  | 200        |
      | 25701755 | 403        |
