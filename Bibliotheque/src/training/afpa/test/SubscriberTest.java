package training.afpa.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import training.afpa.model.Subscriber;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberTest {

    Subscriber subscriber1 = new Subscriber("Adele",
            "Boulanger",
            "adele.boulanger@afpa.training");
    Subscriber subscriber2 = new Subscriber("Del",
            "Langer",
            "del.langer@afpa.training");

    @Test
    public void constructor_GetterAndSetter_ValidInput() {
        assertEquals("adele.boulanger@afpa.training", subscriber1.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        assertEquals(LocalDate.now().format(formatter), subscriber2.getSubDate());
    }

    @ParameterizedTest(name="{0} le setter leve exception avec succes.")
    @ValueSource(strings={"azer@", "azer@ty"})
    public void setEmail_InvalidInput(String email) {
        assertThrows(IllegalArgumentException.class, () -> subscriber1.setEmail(email));
    }

    @Test
    public void searchSubscriberByEmail_ValidInput() {
        Subscriber subscriberForTest = Subscriber.searchSubscriberByEmail("adele.boulanger@afpa.training");
        assertEquals(subscriber1.getFirstName(), subscriberForTest.getFirstName());
    }

    @Test
    public void searchSubscriberByEmail_InvalidInput() {
        Subscriber subscriberForTest = Subscriber.searchSubscriberByEmail("adele.boulanger@afpa.training");
        assertNotEquals(subscriber2.getFirstName(), subscriberForTest.getFirstName());
    }

    @Test
    public void toString_ValidInput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        assertEquals("\nSubscriber{First name: Del, Last name: Langer, email: del.langer@afpa.training, subDate: " +
                LocalDate.now().format(formatter) + "}\n", subscriber2.toString());
    }
}