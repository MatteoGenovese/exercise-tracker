Feature:

  Background:
    Given those students:
      |uuid                                  | username    |
      | 4b05c31e-ceb7-4c9a-b5b9-703e74fbdf1e | usernameTest|
    And those exercises:
      |uuid                                 | description|duration|date                     | studentUuid |
      |9fb3f69b-e3ee-4586-8b83-5ff4feec0e27 |newTest    |60      | 2025-01-01T14:23:45.123  | 4b05c31e-ceb7-4c9a-b5b9-703e74fbdf1e |

  Scenario: As a valid Student I can upload my exercise track under my profile
    Given the student "4b05c31e-ceb7-4c9a-b5b9-703e74fbdf1e"
    And the exercise track "9fb3f69b-e3ee-4586-8b83-5ff4feec0e27"
    When I post the exercise track under the selected profile
    Then The exercise track is added and is linked with the student profile

  Scenario: As a non Registered Student I can't upload my exercise track
    Given the invalid student "b2054378-52c6-4832-b46d-6af185783cc8"
    And the exercise track "9fb3f69b-e3ee-4586-8b83-5ff4feec0e27"
    When I try to post the exercise track under the profile
    Then An error is thrown with message "Student not found"