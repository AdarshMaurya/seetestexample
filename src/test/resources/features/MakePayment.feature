#Author: adarshmaurya@softhinkers.com
#Keywords Summary : Make Payment
#Feature: Make Payment
#Scenario: User Makes Payment
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@sanityTest
Feature: Make Payment
  As an end user I want to make payment
  @android @iphone

  @android @Iphone

  Scenario: Make Payment
    Given User is on Main Activity
    And User clicks on Make Payment
    And User Enters Phone Number
    And User Enters Name
    And User Enters Amount
    And User Selects Country
    And User Clicks Send Payment
    Then Confirmation Dialog Box Pops out
    And User Clicks On Yes
    Then User Balance must decrease
