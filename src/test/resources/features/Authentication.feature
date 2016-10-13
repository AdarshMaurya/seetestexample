#Author: adarshmaurya@softhinkers.com
#Keywords Summary : Authentication
#Feature: Authentication
#Scenario: Authentication- User Login
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
Feature: Authentication
  As an end user I want to login in the application.

  @android @iphone
  Scenario: Authentication- User Login
    Given Login Activity Is On Screen
    When User Enters Valid Username
    And User Enters Valid Password
    And User Click on Login Button
    Then User Navigates to Main Activity