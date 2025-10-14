package com.tomorrowdevs.exercise_tracker.users.domain.model;

import com.tomorrowdevs.exercise_tracker.users.domain.error.InvalidUsername;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UsernameTest {


   @Test
   void test_usernameShouldBeAtLeast8Characters (){
       Throwable exception = assertThrows(
               InvalidUsername.class,
               () -> new Username("pippo")
       );

       assertEquals("Username too short", exception.getMessage());
   }

    @Test
    void test_usernameShouldBeLessThan25Characters (){
        Throwable exception = assertThrows(
                InvalidUsername.class,
                () -> new Username("Username very very very very very very very very very long.")
        );

        assertEquals("Username too long", exception.getMessage());
    }

    @Test
    void test_usernameShouldNotHaveAnyWhiteSpaces (){
        Throwable exception = assertThrows(
                InvalidUsername.class,
                () -> new Username("Username with spaces")
        );

        assertEquals("Username cannot have any spaces", exception.getMessage());
    }

    @Test
    void testGetValue() {
        Username username = new Username("john.smith");

        assertEquals("john.smith", username.getValue());
    }

    @Test
    void testToString() {
        Username username = new Username("john.smith");

        assertEquals("john.smith", username + "");
    }

    @Test
    void testEqualsTo() {
        Username username1 = new Username("john.smith");
        Username username2 = new Username("john.doe");
        Username username3 = new Username("john.smith");

        assertTrue(username1.equalsTo(username1));
        assertFalse(username1.equalsTo(username2));
        assertTrue(username1.equalsTo(username3));
    }
}