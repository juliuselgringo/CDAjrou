package training.afpa.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import training.afpa.model.Character;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import training.afpa.model.Character;
import training.afpa.model.Origin;
import training.afpa.utility.UserInputException;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    Origin origin = new Origin("human");

    @Test
    public void constructorAndGetters() throws UserInputException {
        Character character = new Character("TestPseudo", origin);
        assertEquals("TestPseudo", character.getPseudo());
        assertEquals(origin, character.getOrigin());
    }

    @Test
    public void setPseudo_ValidInput() throws UserInputException {
        Character character = new Character("OldPseudo", origin);
        character.setPseudo("NewPseudo");
        assertEquals("NewPseudo", character.getPseudo());
    }

    @ParameterizedTest(name = "{0} : le setter lève une exception.")
    @ValueSource(strings = {"", "       "})
    public void setPseudo_ThrowsException(String pseudo) throws UserInputException {
        Character character =  new Character("ValidPseudo", origin);
        assertThrows(UserInputException.class, () -> character.setPseudo(pseudo));
    }

    @Test
    public void setPseudo_NullInput() throws NullPointerException, UserInputException {
        Character character = new Character("OldPseudo", origin);
        assertThrows(NullPointerException.class, () -> character.setPseudo(null));
    }

    @Test
    public void setHealthPoint_Test() throws UserInputException {
        Character character = new Character("TestPseudo", origin);
        int initialHP = character.getHealthPoint();
        character.setHealthPoint();
        assertEquals(initialHP + 5, character.getHealthPoint());
    }

    @Test
    public void toString_Test() throws UserInputException {
        Character character = new Character("TestPseudo", origin);
        String expectedString = "TestPseudo is a " + origin.toString();
        assertEquals(expectedString, character.toString());
    }
}