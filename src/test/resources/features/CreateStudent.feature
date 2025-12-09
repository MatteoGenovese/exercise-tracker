Feature: Create Student
  Scenario: Create a new student Successfully
    Given A request for a new student with name "username1"
    When I save the student
    Then the Student can be found into the system

  Scenario: If username has less then eight character should throw an error
    Given A request for a new student with username length < of eight character "matteo"
    Then An error should appear "Username too short"

