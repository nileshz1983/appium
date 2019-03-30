
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario Outline: User SignUp 
    When enter mobile "<mobile_no>"
    And click on sign up button
    Then sign up should be successful


    Examples: 
      | mobile_no  | status  |
      | 8288825021 | success |
