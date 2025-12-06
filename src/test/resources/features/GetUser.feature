Feature: Retrieve user List

  Scenario: User List is empty, return an error
    Given an empty database of users
    When I try to retrieve all the users
    Then an error is thrown with message "Data not found"

  Scenario: Retrieve a user List Successfully
    Given a List of User Persisted in the database
      |uuid|username|
      |add3fa95-750f-46f4-9a12-0c8322008703| username1 |
      |b3ac831f-9676-4148-bf5a-ef27698cc4a9| username2 |
      |2f380d43-04d6-41a6-94fd-ce3644881ba8| username3 |
      |64c830da-7a8e-4d32-a064-ef0865381845| username4 |
    When I retrieve all the users
    Then the user List is retrieved to the client


