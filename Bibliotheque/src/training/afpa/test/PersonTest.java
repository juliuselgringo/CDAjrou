package training.afpa.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import training.afpa.model.Person;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    Person person = new Person("Adele", "Boulanger");

    @Test
    public void constructor_GetterAndSetter_ValidInput(){
        assertEquals("Adele",person.getFirstName());
        assertEquals("Boulanger",person.getLastName());
    }

    @ParameterizedTest(name = "{0} : le setter lève une exception.")
    @ValueSource(strings = {"", "       "})
    public void setFirstName_InvalidInput(String firstName) {
        assertThrows(IllegalArgumentException.class, () -> person.setFirstName(firstName));
    }

    @ParameterizedTest(name = "{0} : le setter lève une exception.")
    @ValueSource(strings = {"", "       "})
    public void setLastName_InvalidInput(String lastName) {
        assertThrows(IllegalArgumentException.class, () -> person.setLastName(lastName));
    }
}