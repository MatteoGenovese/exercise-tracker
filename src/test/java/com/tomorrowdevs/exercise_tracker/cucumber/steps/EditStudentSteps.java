package com.tomorrowdevs.exercise_tracker.cucumber.steps;

import com.tomorrowdevs.exercise_tracker.common.domain.EntityNotFound;
import com.tomorrowdevs.exercise_tracker.cucumber.config.SpringIntegrationTest;
import com.tomorrowdevs.exercise_tracker.users.application.repository.InMemoryStudentRepository;
import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.StudentEditor;
import com.tomorrowdevs.exercise_tracker.users.application.service.StudentWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidUsername;
import com.tomorrowdevs.exercise_tracker.users.domain.error.StudentNotFound;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.ChangeUsernameRequest;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.service.StudentEditorImpl;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.service.StudentWriterImpl;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;


public class EditStudentSteps extends SpringIntegrationTest {

    private StudentWriter studentWriter;
    private StudentRepository studentRepository;
    private StudentEditor studentEditor;

    private Student studentToBeEdited;
    private Student studentEdited;
    private EntityNotFound studentNotFoundMessage;
    private ChangeUsernameRequest changeUsernameRequest;

    public EditStudentSteps(StudentEditor studentEditor) {
        this.studentRepository = new InMemoryStudentRepository();
        this.studentEditor = new StudentEditorImpl(studentRepository);
        this.studentWriter = new StudentWriterImpl(studentRepository);
    }


    @Given("the following ChangeUsernameRequest:")
    public void theFollowingChangeUsernameRequest(DataTable dataTable) {
        Map<String,String> changeStudentRequestAsString = dataTable.asMap(String.class, String.class);
        Username oldUsername = Username.create(changeStudentRequestAsString.get("oldUsername"));

        changeUsernameRequest =
                ChangeUsernameRequest.create(UUID.fromString(changeStudentRequestAsString.get("uuid")),
                                             oldUsername);

        studentToBeEdited = Student.create(changeUsernameRequest.getUuid(),
                                changeUsernameRequest.getUsername());

        studentWriter.save(studentToBeEdited);

    }

    @When("I edit the username")
    public void iEditTheUsername() {
        studentEdited = studentEditor.edit(studentToBeEdited);
    }

    @Then("The student is edited and can be found into the system")
    public void theUserIsEditedAndCanBeFoundIntoTheSystem() {
        Student studentSaved = studentRepository.findByUuid(studentToBeEdited.uuid());
        Assertions.assertEquals(studentEdited.username().getValue(), studentSaved.username().getValue());
        Assertions.assertEquals(studentEdited.uuid().toString(), studentSaved.uuid().toString());
    }

    @Given("a non present student in the database")
    public void aNonPresentStudentInTheDatabase(DataTable dataTable) {

        Map<String,String> changeStudentRequestAsString = dataTable.asMap(String.class,
                                                                       String.class);
        Username oldUsername = Username.create(changeStudentRequestAsString.get("oldUsername"));
        changeUsernameRequest =
                ChangeUsernameRequest.create(UUID.fromString(changeStudentRequestAsString.get("uuid")),
                                             oldUsername);

        studentToBeEdited = Student.create(changeUsernameRequest.getUuid(),
                                     changeUsernameRequest.getUsername());

    }

    @When("I edit a non present username")
    public void iEditANonPresentUsername() {

        studentNotFoundMessage = Assertions.assertThrows(
                EntityNotFound.class,
                () -> studentRepository.editByUuid(studentToBeEdited)
        );

    }

    @Then("An error is throw with message {string}")
    public void anErrorIsThrowWithMessage(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, studentNotFoundMessage.getMessage());
    }

}
