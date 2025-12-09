package com.tomorrowdevs.exercise_tracker.cucumber.steps;

import com.tomorrowdevs.exercise_tracker.cucumber.config.SpringIntegrationTest;
import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.StudentWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidUsername;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.controller.request.StudentCreationRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;


public class CreateStudentSteps extends SpringIntegrationTest {

    private StudentCreationRequest studentCreationRequest;
    private StudentCreationRequest studentCreationRequest2;
    private Student student;
    private Student saved;
    private InvalidUsername invalidUsername;


    private StudentWriter studentWriter;
    private StudentRepository studentRepository;

    @Autowired
    public CreateStudentSteps(StudentWriter studentWriter, StudentRepository studentRepository) {
        this.studentWriter = studentWriter;
        this.studentRepository = studentRepository;
    }

    @Given("A request for a new student with name {string}")
    public void a_request_for_a_new_student_with_name(String usernameAsString) {
        studentCreationRequest = new StudentCreationRequest(new Username(usernameAsString));
        student = Student.create(studentCreationRequest.getUsername().getValue());
    }


    @Given("A request for a new student:")
    public void aRequestForANewStudent() {

    }

    @When("I save the student")
    public void iSaveTheStudent() {
        saved = studentWriter.save(student);
    }

    @Then("the Student can be found into the system")
    public void theStudentCanBeFoundIntoTheSystem() {
        Student studentById = studentRepository.findStudentByUuid(saved.uuid());
        Assertions.assertEquals(studentById.username().getValue(), student.username().getValue());
    }

    @Given("A request for a new student with username length < of eight character {string}")
    public void aRequestForANewStudentWithUsernameLengthOfEightCharacter(String usernameAsString) {
        invalidUsername = Assertions.assertThrows(
                InvalidUsername.class,
                ()-> new StudentCreationRequest(new Username(usernameAsString))
        );
    }

    @Then("An error should appear {string}")
    public void anErrorShouldAppear(String expectedErrorMessage) {
        Assertions.assertEquals(expectedErrorMessage, invalidUsername.getMessage());
    }

}
