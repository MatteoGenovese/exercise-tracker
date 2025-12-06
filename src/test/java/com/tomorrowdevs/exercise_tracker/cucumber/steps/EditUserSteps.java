package com.tomorrowdevs.exercise_tracker.cucumber.steps;

import com.tomorrowdevs.exercise_tracker.cucumber.config.SpringIntegrationTest;
import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserEditor;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.error.UserNotFound;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.ChangeUsernameRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Map;
import java.util.UUID;


public class EditUserSteps extends SpringIntegrationTest {


    private User userPresentIntoTheSystem;
    private User userToBeEdited;
    private User userEdited;
    private Username username;
    private UserNotFound userNotFoundMessage;
    private ChangeUsernameRequest changeUsernameRequest;


    private final UserWriter userWriter;
    private final UserRepository userRepository;
    private final UserEditor userEditor;

    public EditUserSteps(UserWriter userWriter, UserRepository userRepository,
            UserEditor userEditor) {
        this.userWriter = userWriter;
        this.userRepository = userRepository;
        this.userEditor = userEditor;
    }


    @Given("the following ChangeUsernameRequest:")
    public void theFollowingChangeUsernameRequest(DataTable dataTable) {
        Map<String,String> changeUserRequestAsString = dataTable.asMap(String.class, String.class);
        Username oldUsername = Username.create(changeUserRequestAsString.get("oldUsername"));
        changeUsernameRequest =
                ChangeUsernameRequest.create(UUID.fromString(changeUserRequestAsString.get("uuid")),
                                             oldUsername);

        userToBeEdited = User.create(changeUsernameRequest.getUuid(),
                                changeUsernameRequest.getUsername());

        userWriter.save(userToBeEdited);

    }

    @When("I edit the username")
    public void iEditTheUsername() {
        userEdited = userEditor.edit(userToBeEdited);
    }

    @Then("The user is edited and can be found into the system")
    public void theUserIsEditedAndCanBeFoundIntoTheSystem() {
        User userSaved = userRepository.findUserByUuid(userToBeEdited.uuid());
        Assertions.assertEquals(userEdited.username().getValue(), userSaved.username().getValue());
        Assertions.assertEquals(userEdited.uuid().toString(), userSaved.uuid().toString());
    }

    @Given("a non present user in the database")
    public void aNonPresentUserInTheDatabase(DataTable dataTable) {

        Map<String,String> changeUserRequestAsString = dataTable.asMap(String.class,
                                                                       String.class);
        Username oldUsername = Username.create(changeUserRequestAsString.get("oldUsername"));
        changeUsernameRequest =
                ChangeUsernameRequest.create(UUID.fromString(changeUserRequestAsString.get("uuid")),
                                             oldUsername);

        userToBeEdited = User.create(changeUsernameRequest.getUuid(),
                                     changeUsernameRequest.getUsername());

    }

    @When("I edit a non present username")
    public void iEditANonPresentUsername() {

        userNotFoundMessage = Assertions.assertThrows(
                UserNotFound.class,
                () -> userRepository.editUserByUuid(userToBeEdited)
        );

    }

    @Then("An error is throw with message {string}")
    public void anErrorIsThrowWithMessage(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, userNotFoundMessage.getMessage());
    }

}
