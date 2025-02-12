package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testPersonConstructor_validData() {
        Person person = Person.builder()
                .id("1")
                .firstName("Geroge")
                .lastName("Malley")
                .age(25)
                .gender("Male")
                .build();

        assertNotNull(person);
        assertEquals("1", person.getId());
        assertEquals("Geroge", person.getFirstName());
        assertEquals("Malley", person.getLastName());
        assertEquals(25, person.getAge());
        assertEquals("Male", person.getGender());
    }

    @Test
    void testPersonConstructor_invalidData() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                Person.builder().id(null).firstName("Geroge").lastName("Malley").age(25).gender("Male").build()
        );
        assertEquals("ID cannot be null", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () ->
                Person.builder().id("1").firstName(null).lastName("Malley").age(25).gender("Male").build()
        );
        assertEquals("First Name cannot be null or blank", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () ->
                Person.builder().id("1").firstName("Geroge").lastName(null).age(25).gender("Male").build()
        );
        assertEquals("Last Name cannot be null or blank", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () ->
                Person.builder().id("1").firstName("Geroge").lastName("Malley").age(-1).gender("Male").build()
        );
        assertEquals("Age cannot be negative", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () ->
                Person.builder().id("1").firstName("Geroge").lastName("Malley").age(25).gender(null).build()
        );
        assertEquals("Gender cannot be null or blank", thrown.getMessage());
    }
}
