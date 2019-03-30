
Feature: AddUserDetails.

Background: 

      
  Scenario Outline: Add user details
    Given navigate to URL
    When user click on AddUserDeatils Link
    And enter firstName "<firstName>"
    And enter lastName "<lastName>"
    And enter username "<username>"
    And enter password "<password>"
    And select customer "<customer>"
    And select Role "<role>"
    And enter your email address "<emailaddress>"
    And Enter mobile phone nymber "<mobilePhoneNumber>"
    And click on save button


    Examples: 
      | firstName|lastName| username|password|customer  | role | emailaddress |mobilePhoneNumber|
      | FName1 | LName1 | User1 |   Pass1 | Company AAA |Admin|admin@gmail.com|082666|
      | FName2 | LName2 | User2 |   Pass2 |    Company BBB | Customer |cutsomer@gmail.com|089765|