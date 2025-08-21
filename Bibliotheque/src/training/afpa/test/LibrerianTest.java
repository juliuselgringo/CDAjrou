package training.afpa.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import training.afpa.model.Librerian;

import static org.junit.jupiter.api.Assertions.*;

public class LibrerianTest {

    Librerian librerian = new Librerian("Jean","Duboncoin","JeDub");

    @Test
    public void constructor_GetterAndSetter_ValidInput() {
        assertEquals("Jean", librerian.getFirstName());
        assertEquals("Duboncoin", librerian.getLastName());
        assertEquals("JeDub", librerian.getId());
    }

    @ParameterizedTest(name = "{0}: setter leve exception avec succès.")
    @ValueSource(strings = {"","     "})
    public void setId_InvalidInput(String id){
        assertThrows(IllegalArgumentException.class, () -> librerian.setId(id));
    }

    @Test
    public void toString_ValidInput() {
        assertEquals("Librerian{First name: Jean, Last name: Duboncoin, id: JeDub}", librerian.toString());
    }
}