package com.tomorrowdevs.exercise_tracker.cucumber.steps;

import com.tomorrowdevs.exercise_tracker.users.application.service.UserReader;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.error.UserEmptyList;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class GetUserSteps {

    UserWriter userWriter;

    UserReader userReader;
    private List<User> userList;

    private UserEmptyList dataNotFound;

    public GetUserSteps(UserWriter userWriter, UserReader userReader) {
        this.userWriter = userWriter;
        this.userReader = userReader;
    }


    @Given("an empty database of users")
    public void anEmptyDatabaseOfUsers() {
        userList = List.of();
    }

    @When("I try to retrieve all the users") public void iTryToRetrieveAllTheUsers() {

        dataNotFound = assertThrows(UserEmptyList.class,
                                    () -> userReader.read());

    }

    @Then("an error is thrown with message {string}")
    public void anErrorIsThrownWithMessage(String exceptionMessage) {

        assertEquals(exceptionMessage, dataNotFound.getMessage());
    }

    @Given("a List of User Persisted in the database")
    public void a_List_of_User_Persisted_in_the_database(DataTable dataTable) {


        List <Map <String,String>> changeUserRequestAsString = dataTable.asMaps(String.class,
                                                                               String.class);
        for( Map <String,String> userAsString: changeUserRequestAsString ){
            userWriter.save(User.create(
                    UUID.fromString(userAsString.get("uuid")),
                    userAsString.get("username")));
        }
    }

    @When("I retrieve all the users")
    public void i_retrieve_all_the_users() {
        userList = userReader.read();
    }

    @Then("the user List is retrieved to the client")
    public void the_user_List_is_retrieved_to_the_client() {
        assertEquals(4, userList.size());
    }




}
