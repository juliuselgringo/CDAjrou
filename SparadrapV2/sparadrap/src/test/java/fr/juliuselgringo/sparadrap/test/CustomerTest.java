package fr.juliuselgringo.sparadrap.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer test = new Customer();

    @Test
    void constructor5_ValidInput() throws InputException {
        Customer customer = new Customer();
        assertInstanceOf(Customer.class, customer);
    }

    @Test
    void constructor1_ValidInput() throws InputException {
        Customer customer = new Customer("Je", "Suis", 1, "123456789012345",
                "01-01-2000", 1,1);
        assertInstanceOf(Customer.class, customer);
        assertEquals("Je", customer.getFirstName());
        assertEquals("Suis", customer.getLastName());
        assertEquals(1, customer.getContactId());
        assertEquals("123456789012345", customer.getSocialSecurityId());
        assertEquals(1, customer.getMutualId());
        assertEquals(1, customer.getDoctorId());
    }

    @Test
    void constructor2_ValidInput() throws InputException {
        Customer customer = new Customer(1, "Je", "Suis", 1, "123456789012345",
                "01-01-2000", 1,1);

        assertInstanceOf(Customer.class, customer);
        assertEquals(1, customer.getCustomerId());
        assertEquals("Je", customer.getFirstName());
        assertEquals("Suis", customer.getLastName());
        assertEquals(1, customer.getContactId());
        assertEquals("123456789012345", customer.getSocialSecurityId());
        assertEquals(1, customer.getMutualId());
        assertEquals(1, customer.getDoctorId());
    }

    @Test
    void constructor3_ValidInput() throws InputException {
        Customer customer = new Customer("Je", "Suis", 1);

        assertInstanceOf(Customer.class, customer);
        assertEquals("Je", customer.getFirstName());
        assertEquals("Suis", customer.getLastName());
        assertEquals(1, customer.getContactId());
    }

    @Test
    void constructor4_ValidInput() throws InputException {
        Customer customer = new Customer("Je", "Suis", "123456789012345");

        assertInstanceOf(Customer.class, customer);
        assertEquals("Je", customer.getFirstName());
        assertEquals("Suis", customer.getLastName());
        assertEquals("123456789012345", customer.getSocialSecurityId());
    }

    @Test
    void set_mutualId() throws InputException {
        Customer customer = new Customer("Je", "Suis", "123456789012345");
        customer.setMutualId(1);
        assertEquals(1,customer.getMutualId());
    }

    @Test
    void set_doctorId() throws InputException {
        Customer customer = new Customer("Je", "Suis", "123456789012345");
        customer.setDoctorId(1);
        assertEquals(1,customer.getDoctorId());
    }

    @Test
    void getDateOfBirth() throws InputException {
        Customer customer = new Customer(1, "Je", "Suis", 1, "123456789012345",
                "01-01-2000", 1,1);
        assertEquals("2000-01-01", customer.getDateOfBirth().toString());
    }

    @ParameterizedTest(name="{0} le setter leve correctement l exception")
    @ValueSource(strings={"","       ", "1234", "/*<>"})
    void setterSocialSecurityId_InvalidInput(String socialSecurityId) throws InputException {
        assertThrows(InputException.class, () -> test.setSocialSecurityId(socialSecurityId));
    }

    @ParameterizedTest(name="{0} le setter leve correctement l exception")
    @ValueSource(strings={"","       ", "1234", "/*<>", "2026-01-01"})
    void setterDateOfBirth_InvalidInput(String dateOf) throws InputException {
        assertThrows(InputException.class, () -> test.setDateOfBirth(dateOf));
    }

    @Test
    public void setterDateOfBirth_invalidInput() throws InputException {
        InputException thrown = assertThrows(InputException.class, () -> test.setDateOfBirth("01-01-2026"));
        assertEquals("La date de naissance ne peut être postérieure à la date d'aujourd'hui", thrown.getMessage());
    }



}