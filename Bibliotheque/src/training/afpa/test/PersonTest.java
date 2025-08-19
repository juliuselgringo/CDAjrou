package training.afpa.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import training.afpa.model.Person;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void constructorGetterAndSetter_ValidInput(){
        Person person = new Person("Adele", "Boulanger");
        assertEquals("Adele",person.getFirstName());
        assertEquals("Boulanger",person.getLastName());
    }

    @ParameterizedTest(name = "{0} : le setter lève une exception.")
    @ValueSource(strings = {"", "       "})
    public void setFirstName_InvalidInput(String firstName){
        Person person = new Person("Adele", "Boulanger");
        assertThrows(IllegalArgumentException.class, () -> person.setFirstName(firstName));
    }

}