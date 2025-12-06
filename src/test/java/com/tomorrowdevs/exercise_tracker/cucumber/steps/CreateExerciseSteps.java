package com.tomorrowdevs.exercise_tracker.cucumber.steps;

import com.tomorrowdevs.exercise_tracker.exercises.application.repository.ExerciseRepository;
import com.tomorrowdevs.exercise_tracker.exercises.application.repository.InMemoryExerciseRepository;
import com.tomorrowdevs.exercise_tracker.exercises.application.service.ExerciseTracker;
import com.tomorrowdevs.exercise_tracker.exercises.domain.model.Exercise;
import com.tomorrowdevs.exercise_tracker.exercises.domain.model.ExerciseDescription;
import com.tomorrowdevs.exercise_tracker.exercises.domain.model.TimeDuration;
import com.tomorrowdevs.exercise_tracker.exercises.infrastructure.service.ExerciseTrackerImpl;
import com.tomorrowdevs.exercise_tracker.users.application.repository.InMemoryUserRepository;
import com.tomorrowdevs.exercise_tracker.users.application.repository.UserRepository;
import com.tomorrowdevs.exercise_tracker.users.application.service.UserWriter;
import com.tomorrowdevs.exercise_tracker.users.domain.error.UserNotFound;
import com.tomorrowdevs.exercise_tracker.users.domain.model.User;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
import com.tomorrowdevs.exercise_tracker.users.infrastructure.service.UserWriterImpl;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class CreateExerciseSteps {

    private UserWriter userWriter;
    private ExerciseTracker exerciseTracker;
    private UserRepository userRepository;
    private ExerciseRepository exerciseRepository;


    private User userInDb;
    private Exercise exerciseInDb;
    private UUID wrongUserId;
    private User userNotPresent;
    private User userSaved;
    private Exercise exerciseSaved;
    private UserNotFound userNotFoundException;


    public CreateExerciseSteps(
            ExerciseTracker exerciseTracker,
            ExerciseRepository exerciseRepository
    ) {

        this.userRepository = new InMemoryUserRepository();
        this.userWriter = new UserWriterImpl(userRepository);

        this.exerciseRepository = new InMemoryExerciseRepository();
        this.exerciseTracker = new ExerciseTrackerImpl(
                exerciseRepository,
                userRepository
        );
    }

    @Given("those users:")
    public void thoseUsers(DataTable dataTable) {
        List<Map<String, String>> users = dataTable.asMaps(
                String.class,
                String.class
        );

        for (Map<String, String> user : users) {

            UUID uuid = UUID.fromString(user.get("uuid"));
            Username username = Username.create(user.get("username"));

            userRepository.save(User.create(
                    uuid,
                    username
            ));
        }
    }


    @And("those exercises:")
    public void thoseExercises(DataTable dataTable) {
        List<Map<String, String>> exercisesMapAsString = dataTable.asMaps(
                String.class,
                String.class
        );


        for (Map<String, String> exercise : exercisesMapAsString) {

            UUID uuid = UUID.fromString(exercise.get("uuid"));

            LocalDateTime date = LocalDateTime.parse(exercise.get("date"));
            ExerciseDescription description = ExerciseDescription.create(exercise.get("description"));
            TimeDuration duration = TimeDuration.create(exercise.get("duration"));
            UUID studentUuid = UUID.fromString(exercise.get("studentUuid"));

            Exercise newExercise = Exercise.create(
                    uuid,
                    date,
                    description,
                    duration,
                    studentUuid
            );

            exerciseRepository.save(newExercise);
        }


    }

    @Given("the user {string}")
    public void theUser(String userUuid) {
        userInDb = userRepository.findUserByUuid(UUID.fromString(userUuid));
        assertNotNull(userInDb);
    }

    @And("the exercise track {string}")
    public void theExerciseTrack(String exerciseUuid) {
        exerciseInDb = exerciseRepository.find(UUID.fromString(exerciseUuid));
        assertNotNull(exerciseInDb);

    }

    @When("I post the exercise track under the selected profile")
    public void iPostTheExerciseTrackUnderTheSelectedProfile() {
        exerciseSaved = exerciseTracker.saveExerciseTrack(exerciseInDb);
    }

    @Then("The exercise track is added and is linked with the user profile")
    public void the_exercise_track_is_added_and_is_linked_with_the_user_profile() {
        assertEquals(
                exerciseInDb.dateTime(),
                exerciseSaved.dateTime()
        );
        assertEquals(
                exerciseInDb.duration(),
                exerciseSaved.duration()
        );
        assertEquals(
                exerciseInDb.studentUuid(),
                exerciseSaved.studentUuid()
        );
        assertEquals(
                exerciseInDb.description(),
                exerciseSaved.description()
        );
    }

    @Given("the invalid user {string}")
    public void theInvalidUser(String uuidAsString) {
        wrongUserId = UUID.fromString(uuidAsString);
        userNotPresent = userRepository.findUserByUuid(wrongUserId);
        assertNull(userNotPresent);
    }

    @When("I try to post the exercise track under the profile")
    public void iTryToPostTheExerciseTrackUnderTheProfile() {

        Exercise exercise = Exercise.create(
                LocalDateTime.now(),
                ExerciseDescription.create("a random description"),
                TimeDuration.create(38),
                wrongUserId
        );

        userNotFoundException = assertThrows(
                UserNotFound.class,
                () -> exerciseTracker.saveExerciseTrack(exercise)
        );
    }

    @Then("An error is thrown with message {string}")
    public void anErrorIsThrownWithMessage(String userNotFoundMessage) {
        Assertions.assertEquals(userNotFoundMessage, userNotFoundException.getMessage());

    }

    //    @Given("a non Registered User with uuid {string} and a valid exercise track:")
//    public void a_non_Registered_User_with_uuid_and_a_valid_exercise_track(String uuidAsString,
//            DataTable dataTable) {
//        uuidNotPresentInDb = uuidAsString;
//        Map<String, String> validExerciseData = dataTable.asMap(String.class, String.class);
//
//        notValidUserUuidInExerciseTrack = ExerciseTrack.create(
//                LocalDateTime.parse(validExerciseData.get("date")),
//                ExerciseDescription.create(validExerciseData.get("description")),
//                TimeDuration.create(validExerciseData.get("duration")),
//                UUID.fromString(uuidAsString)
//        );
//    }
//
//
//    @Then("An error is thrown with message {string}")
//    public void an_error_is_thrown_with_message(String string) {
//        Assertions.assertEquals("User not found", userNotFoundException.getMessage());
//    }


}
