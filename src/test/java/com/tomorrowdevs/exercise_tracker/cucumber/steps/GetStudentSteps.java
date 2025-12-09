package com.tomorrowdevs.exercise_tracker.cucumber.steps;

import com.tomorrowdevs.exercise_tracker.users.application.service.StudentReader;
import com.tomorrowdevs.exercise_tracker.users.application.service.StudentWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.error.StudentEmptyList;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GetStudentSteps {

    StudentWriter studentWriter;

    StudentReader studentReader;
    private List<Student> studentList;

    private StudentEmptyList dataNotFound;

    public GetStudentSteps(StudentWriter studentWriter, StudentReader studentReader) {
        this.studentWriter = studentWriter;
        this.studentReader = studentReader;
    }


    @Given("an empty database of students")
    public void anEmptyDatabaseOfStudents() {
        studentList = List.of();
    }

    @When("I try to retrieve all the students")
    public void iTryToRetrieveAllTheStudents() {

        dataNotFound = assertThrows(
                StudentEmptyList.class,
                () -> studentReader.read()
        );

    }

    @Then("an error is thrown with message {string}")
    public void anErrorIsThrownWithMessage(String exceptionMessage) {

        assertEquals(exceptionMessage, dataNotFound.getMessage());
    }

    @Given("a List of Student Persisted in the database")
    public void a_List_of_Student_Persisted_in_the_database(DataTable dataTable) {


        List <Map <String,String>> changeStudentRequestAsString = dataTable.asMaps(String.class,
                                                                               String.class);
        for( Map <String,String> studentAsString: changeStudentRequestAsString ){
            studentWriter.save(Student.create(
                    UUID.fromString(studentAsString.get("uuid")),
                    studentAsString.get("username")));
        }
    }

    @When("I retrieve all the students")
    public void i_retrieve_all_the_students() {
        studentList = studentReader.read();
    }

    @Then("the student List is retrieved to the client")
    public void the_student_List_is_retrieved_to_the_client() {
        assertEquals(4, studentList.size());
    }




}
