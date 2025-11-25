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

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MutualTest {

    MutualTest() throws InputException {
    }

    @Test
    void contstructor3_MutualTest_ValidInput() throws InputException {
        Mutual mutualTest = new Mutual("La", 1, 0.2);
        assertEquals("La", mutualTest.getName());
        assertEquals(1, mutualTest.getContactId());
        assertEquals(0.2, mutualTest.getRate());
    }

    @Test
    void contstructor2_MutualTest_ValidInput() throws InputException {
        Mutual mutualTest = new Mutual(1,"La", 1, 0.2);
        assertEquals(1, mutualTest.getMutualId());
        assertEquals("La", mutualTest.getName());
        assertEquals(1, mutualTest.getContactId());
        assertEquals(0.2, mutualTest.getRate());
    }

    @Test
    void contstructor1_MutualTest_ValidInput() throws InputException {
        Mutual mutualTest = new Mutual();
        assertInstanceOf(Mutual.class, mutualTest);
    }

    @Test
    void setget_contactId() throws InputException {
        Mutual mutualTest = new Mutual("La", 1, 0.2);
        mutualTest.setContactId(1);
        assertEquals(1, mutualTest.getContactId());
    }

    @Test
    void setgetMutualId() throws InputException {
        Mutual mutualTest = new Mutual("La", 1, 0.2);
        mutualTest.setMutualId(1);
        assertEquals(1, mutualTest.getMutualId());
    }


}