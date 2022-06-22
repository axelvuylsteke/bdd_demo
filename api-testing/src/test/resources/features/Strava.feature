@Strava @Tryout

Feature: Testing my Strava data

  Scenario Outline: Getting my strava stats
    Given I have my Strava API access token
    When I retrieve the <userId> stats
    Then I receive a <statusCode> response code
    And I get the user all-time stats

    Examples:
      | userId  | statusCode |
      | 1067810 | 200        |