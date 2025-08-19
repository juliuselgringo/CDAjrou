package training.afpa.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import training.afpa.model.Subscriber;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberTest {

    Subscriber subscriber1 = new Subscriber("Adele",
            "Boulanger",
            "adele.boulanger@afpa.training");

    @Test
    public void constructor_GetterAndSetter_ValidInput() {
        assertEquals("adele.boulanger@afpa.training", subscriber1.getEmail());
    }

    @ParameterizedTest(name="{0} le setter leve exception avec succes.")
    @ValueSource(strings={"azer@", "azer@ty"})
    public void setEmail_InvalidInput(String email) {
        assertThrows(IllegalArgumentException.class, () -> subscriber1.setEmail(email));
    }
}