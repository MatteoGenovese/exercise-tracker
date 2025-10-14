Feature: Edit User
  Scenario: Edit a new user Successfully
    Given I want to modify an user with uuid "4b05c31e-ceb7-4c9a-b5b9-703e74fbdf1e" with name "matteo.genovese.91" a new name "matteo.genovese.92"
    When I edit the username
    And The user is edited and can be found into the system
