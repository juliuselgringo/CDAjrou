package fr.juliuselgringo.sparadrap.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Contact;
import fr.juliuselgringo.sparadrap.model.Customer;
import fr.juliuselgringo.sparadrap.model.Doctor;
import fr.juliuselgringo.sparadrap.model.Mutual;

import static org.junit.jupiter.api.Assertions.*;

class MutualTest {

    Contact contact = new Contact("3 rue machin","54000","Machin","00 00 00 00 00","ma@chin.fr");
    Mutual test = new Mutual("Machin",contact,0.4);

    MutualTest() throws InputException {
    }

    @Test
    public void constructor_GetterAndSetter_ValidInput() throws InputException {
        assertEquals("Machin", test.getName());
        assertEquals("3 rue machin", test.getContact().getAddress());
        assertEquals("54000", test.getContact().getPostalCode());
        assertEquals("Machin", test.getContact().getTown());
        assertEquals("00 00 00 00 00", test.getContact().getPhone());
        assertEquals("ma@chin.fr", test.getContact().getEmail());
        assertEquals(0.4, test.getRate());
    }

    @ParameterizedTest(name="{0} le setter lève correctemnt l exception")
    @ValueSource(strings={"","    ", "machin", "2000"})
    public void setterName_InvalidInput(String input) throws InputException {
        assertThrows(InputException.class, (Executable) () -> test.setName(input));
    }

    @ParameterizedTest(name="{0} le setter lève correctemnt l exception")
    @ValueSource(doubles={ 2.00, 2000, -4})
    public void setter_InvalidInput(Double input) throws InputException {
        assertThrows(InputException.class, (Executable) () -> test.setRate(input));
    }

    @Test
    public void getMutualCustomersListMatrice_ValidInput() throws InputException {
        Contact contact = new Contact();
        Doctor doctor = new Doctor();
        Mutual test = new Mutual("Test",contact,0.4);
        Customer customer = new Customer("Machin","Test", contact,"123456789012345", "12-01-1995", test, doctor);
        assertEquals("Test", test.getMutualCustomersListMatrice()[0][0]);
    }

    @Test
    public void emptyConstructor_setContact_ValidInput() throws InputException {
        Mutual test = new Mutual();
        Contact contact = new Contact();
        test.setContact(contact);
    }

}