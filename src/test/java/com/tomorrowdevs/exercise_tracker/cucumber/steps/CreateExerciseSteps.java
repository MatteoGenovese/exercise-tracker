package com.tomorrowdevs.exercise_tracker.cucumber.steps;

import com.tomorrowdevs.exercise_tracker.exercises.application.repository.ExerciseRepository;
import com.tomorrowdevs.exercise_tracker.exercises.application.repository.InMemoryExerciseRepository;
import com.tomorrowdevs.exercise_tracker.exercises.application.service.ExerciseTracker;
import com.tomorrowdevs.exercise_tracker.exercises.domain.model.Exercise;
import com.tomorrowdevs.exercise_tracker.exercises.domain.model.ExerciseDescription;
import com.tomorrowdevs.exercise_tracker.exercises.domain.model.TimeDuration;
import com.tomorrowdevs.exercise_tracker.exercises.infrastructure.service.ExerciseTrackerImpl;
import com.tomorrowdevs.exercise_tracker.users.application.repository.InMemoryStudentRepository;
import com.tomorrowdevs.exercise_tracker.users.application.repository.StudentRepository;
import com.tomorrowdevs.exercise_tracker.users.domain.error.StudentNotFound;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Student;
import com.tomorrowdevs.exercise_tracker.users.domain.model.Username;
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

    private  ExerciseTracker exerciseTracker;
    private  StudentRepository studentRepository;
    private  ExerciseRepository exerciseRepository;

    private Exercise exerciseInDb;
    private Exercise exerciseSaved;
    private StudentNotFound studentNotFoundException;
    private UUID wrongStudentId;

    public CreateExerciseSteps() {
        this.studentRepository = new InMemoryStudentRepository();
        this.exerciseRepository = new InMemoryExerciseRepository();
        this.exerciseTracker = new ExerciseTrackerImpl(
                exerciseRepository,
                studentRepository
        );
    }

    @Given("those students:")
    public void thoseStudents(DataTable dataTable) {
        List<Map<String, String>> students = dataTable.asMaps(
                String.class,
                String.class
        );

        for (Map<String, String> student : students) {

            UUID uuid = UUID.fromString(student.get("uuid"));
            Username username = Username.create(student.get("username"));

            studentRepository.save(Student.create(
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

    @Given("the student {string}")
    public void theStudent(String studentUuid) {
        Student studentInDb = studentRepository.findByUuid(UUID.fromString(studentUuid));
        assertNotNull(studentInDb);
    }

    @And("the exercise track {string}")
    public void theExerciseTrack(String exerciseUuid) {
        exerciseInDb = exerciseRepository.findByUuid(UUID.fromString(exerciseUuid));
        assertNotNull(exerciseInDb);

    }

    @When("I post the exercise track under the selected profile")
    public void iPostTheExerciseTrackUnderTheSelectedProfile() {
        exerciseSaved = exerciseTracker.saveExerciseTrack(exerciseInDb);
    }

    @Then("The exercise track is added and is linked with the student profile")
    public void the_exercise_track_is_added_and_is_linked_with_the_student_profile() {
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

    @Given("the invalid student {string}")
    public void theInvalidStudent(String uuidAsString) {
        wrongStudentId = UUID.fromString(uuidAsString);
        Student studentNotPresent = studentRepository.findByUuid(wrongStudentId);
        assertNull(studentNotPresent);
    }

    @When("I try to post the exercise track under the profile")
    public void iTryToPostTheExerciseTrackUnderTheProfile() {

        Exercise exercise = Exercise.create(
                LocalDateTime.now(),
                ExerciseDescription.create("a random description"),
                TimeDuration.create(38),
                wrongStudentId
        );

        studentNotFoundException = assertThrows(
                StudentNotFound.class,
                () -> exerciseTracker.saveExerciseTrack(exercise)
        );
    }

    @Then("An error is thrown with message {string}")
    public void anErrorIsThrownWithMessage(String studentNotFoundMessage) {
        Assertions.assertEquals(studentNotFoundMessage, studentNotFoundException.getMessage());

    }


}
