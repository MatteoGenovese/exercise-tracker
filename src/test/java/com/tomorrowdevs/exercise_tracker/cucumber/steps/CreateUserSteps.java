package com.tomorrowdevs.exercise_tracker.cucumber.steps;

import com.tomorrowdevs.exercise_tracker.cucumber.config.SpringIntegrationTest;
import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidUsername;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.UserCreationRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;


public class CreateUserSteps extends SpringIntegrationTest {

    private UserCreationRequest userCreationRequest;
    private UserCreationRequest userCreationRequest2;
    private User user;
    private User saved;
    private InvalidUsername invalidUsername;


    private UserWriter userWriter;
    private UserRepository userRepository;

    @Autowired
    public CreateUserSteps(UserWriter userWriter, UserRepository userRepository) {
        this.userWriter = userWriter;
        this.userRepository = userRepository;
    }

    @Given("A request for a new user with name {string}")
    public void a_request_for_a_new_user_with_name(String usernameAsString) {
        userCreationRequest = new UserCreationRequest(new Username(usernameAsString));
        user = User.create(userCreationRequest.getUsername().getValue());
    }


    @Given("A request for a new user:")
    public void aRequestForANewUser() {

    }

    @When("I save the user")
    public void iSaveTheUser() {
        saved = userWriter.save(user);
    }

    @Then("the User can be found into the system")
    public void theUserCanBeFoundIntoTheSystem() {
        User userById = userRepository.findUserByUuid(saved.uuid());
        Assertions.assertEquals(userById.username().getValue(), user.username().getValue());
    }

    @Given("A request for a new user with username length < of eight character {string}")
    public void aRequestForANewUserWithUsernameLengthOfEightCharacter(String usernameAsString) {
        invalidUsername = Assertions.assertThrows(
                InvalidUsername.class,
                ()-> new UserCreationRequest(new Username(usernameAsString))
        );
    }

    @Then("An error should appear {string}")
    public void anErrorShouldAppear(String expectedErrorMessage) {
        Assertions.assertEquals(expectedErrorMessage, invalidUsername.getMessage());
    }

}
