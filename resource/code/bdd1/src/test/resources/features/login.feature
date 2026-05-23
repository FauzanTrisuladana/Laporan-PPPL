Feature: SauceDemo Login

  Scenario Outline: Successful login with valid users
    Given User is on the SauceDemo login page
    When User logs in with username "<username>" and password "secret_sauce"
    Then User should be redirected to inventory page
    And Inventory page should be displayed

    Examples:
      | username                |
      | standard_user           |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |

  Scenario: Locked out user cannot login
    Given User is on the SauceDemo login page
    When User logs in with username "locked_out_user" and password "secret_sauce"
    Then User should not be redirected to inventory page
