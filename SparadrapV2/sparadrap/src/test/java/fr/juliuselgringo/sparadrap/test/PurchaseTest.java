package fr.juliuselgringo.sparadrap.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {

    PurchaseTest() throws InputException {
    }

    @Test
    void constructor_ValidInput() {
        Purchase test = new Purchase(true);
        assertInstanceOf(Purchase.class, test);
        assertEquals(true, test.getWithPrescription());
    }

    @Test
    void constructor2_ValidInput() throws InputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Purchase test = new Purchase("01-08-2025",true);
        assertInstanceOf(Purchase.class, test);
        assertEquals(true, test.getWithPrescription());
        assertEquals("01-08-2025",test.getPurchaseDate().format(formatter));
    }

}