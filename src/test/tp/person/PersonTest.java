package tp.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private final Person person = new Person("1234", "john",
            "12341234", "3600@gmail.com");

    @Test
    void getId() {
        assertEquals("1234", person.getId());
    }

    @Test
    void getName() {
        assertEquals("john", person.getName());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("12341234", person.getPhoneNumber());
    }

    @Test
    void getEmail() {
        assertEquals("3600@gmail.com", person.getEmail());
    }

    @Test
    void testToString() {
        assertEquals("[1234] || Name: john " +
                             "|| Contact No.: 12341234 || Email: 3600@gmail.com", person.toString());
    }
}
