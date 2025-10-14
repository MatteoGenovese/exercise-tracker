package com.tomorrowdevs.exercise_tracker.cucumber.steps;

import com.tomorrowdevs.exercise_tracker.cucumber.config.SpringIntegrationTest;
import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserEditor;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.UserEditRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;


public class EditUserSteps extends SpringIntegrationTest {


    private User userPresentIntoTheSystem;
    private User userToBeEdited;
    private User userEdited;
    private UserEditRequest userEditRequest;
    private Username username;


    private final UserWriter userWriter;
    private final UserRepository userRepository;
    private final UserEditor userEditor;

    public EditUserSteps(UserWriter userWriter, UserRepository userRepository,
            UserEditor userEditor) {
        this.userWriter = userWriter;
        this.userRepository = userRepository;
        this.userEditor = userEditor;
    }

    @Given("I want to modify an user with uuid {string} with name {string} a new name {string}")
    public void iWantToModifyAnUserWithUuidWithNameANewName(String uuid, String oldUsername,
            String newUsername) {
        userPresentIntoTheSystem = new User(UUID.fromString(uuid), Username.create(oldUsername));
        userWriter.save(userPresentIntoTheSystem);

        userEditRequest = new UserEditRequest(UUID.fromString(uuid), Username.create(newUsername));

    }

    @When("I edit the username")
    public void iEditTheUsername() {
        userToBeEdited = new User(userEditRequest.getUuid(),
                                  userEditRequest.getUsername());
        userEdited = userEditor.edit(userToBeEdited);

    }

    @And("The user is edited and can be found into the system")
    public void theUserIsEditedAndCanBeFoundIntoTheSystem() {
        User userSaved = userRepository.findUserByUuid(userToBeEdited.uuid().toString());

        Assertions.assertEquals( userEditRequest.getUsername().getValue(), userSaved.username().getValue());
        Assertions.assertEquals(userEditRequest.getUuid().toString(),
                                userSaved.uuid().toString());
    }
}
