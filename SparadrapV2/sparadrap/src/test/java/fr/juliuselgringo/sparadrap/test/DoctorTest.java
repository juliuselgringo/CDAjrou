package fr.juliuselgringo.sparadrap.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.*;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    DoctorTest() throws InputException {
    }

    @Test
    void constructor2_ValidInput() throws InputException {
        Doctor jeDupParis = new Doctor();
        assertInstanceOf(Doctor.class, jeDupParis);

    }

    @ParameterizedTest(name="{0} le setter leve correctment l exception")
    @ValueSource(strings={"", "         ","aztuiy", ":;,"})
    public void setAgreementId_InvalidInput(String agreementId) throws InputException {
        Doctor testDoctor = new Doctor();
        Customer customerTest = new Customer();
        assertThrows(InputException.class, (Executable) () -> {testDoctor.setAgreementId(agreementId);});
    }


}