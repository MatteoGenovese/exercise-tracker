Feature: Create User
  Scenario: Create a new user Successfully
    Given A request for a new user with name "username1"
    When I save the user
    Then the User can be found into the system

  Scenario: If username has less then eight character should throw an error
    Given A request for a new user with username length < of eight character "matteo"
    Then An error should appear "Username too short"

