@RbaAPITest
Feature: Pet Store API Testing
  Scenario: CRUD a PET
    Given user create a PET
      | ID  | Name     | ExpectedHttpResponseCode |
      | 999 | dream999 | 200                      |
      | 888 | dream888 | 200                      |
    And user get the PET
      | ID  | Name     | ExpectedHttpResponseCode |
      | 999 | dream999 | 200                      |
      | 888 | dream888 | 200                      |
    Then user update the PET with a new name
      | ID  | Name             | ExpectedHttpResponseCode |
      | 999 | dream999_updated | 200                      |
      | 888 | dream888_updated | 200                      |
    And user get the PET
      | ID  | Name             | ExpectedHttpResponseCode |
      | 999 | dream999_updated | 200                      |
      | 888 | dream888_updated | 200                      |
    Then user delete the PET
      | ID  | ExpectedHttpResponseCode |
      | 999 | 200                      |
      | 888 | 200                      |
    And verify PET is deleted
      | ID  | Message       | ExpectedHttpResponseCode |
      | 999 | Pet not found | 404                      |
      | 888 | Pet not found | 404                      |