Feature: Edit User

  Scenario: Edit a new student Successfully
    Given the following ChangeUsernameRequest:
      |oldUsername| oldUsername                                     |
      |uuid       | e2f536cb-e6a3-497c-a60c-c1a68a2c5bc5            |
      |username   | matteo.genovese.91                              |
    When I edit the username
    Then The student is edited and can be found into the system

  Scenario: Can't edit student
    Given a non present student in the database
      |oldUsername| oldUsername                                     |
      |uuid       | 8b9f5f7c-3e2d-4c9b-8f6a-0a72e0e3a5c9            |
      |username   | matteo.nuovoutente                              |
    When I edit a non present username
    Then An error is throw with message "Student not found"
