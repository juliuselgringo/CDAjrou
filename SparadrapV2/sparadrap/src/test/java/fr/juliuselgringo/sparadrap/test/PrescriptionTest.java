package fr.juliuselgringo.sparadrap.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Customer;
import fr.juliuselgringo.sparadrap.model.Doctor;
import fr.juliuselgringo.sparadrap.model.Prescription;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionTest {

    @Test
    public void toString_Test() throws InputException {
        Customer customer = new Customer();
        customer.setLastName("Dupont");
        Doctor doctor = new Doctor();
        doctor.setLastName("Ducoin");
        Prescription prescription = new Prescription("01-09-2025", "Ducoin","Dupont");
        assertEquals("Date: " + "01-09-2025" +
                "\nNom du medecin: " + "Ducoin" +
                "\nNom du client:  " + "Dupont" +
                "\nListe des médicament: ", prescription.toString());
    }

    @Test
    public void toStringForPdf_Test() throws InputException {
        Customer customer = new Customer();
        customer.setLastName("Dupont");
        Doctor doctor = new Doctor();
        doctor.setLastName("Ducoin");
        Prescription prescription = new Prescription("01-09-2025", "Ducoin","Dupont");
        assertEquals(" // " + "01-09-2025" +
                " / Medecin: " + "Ducoin" +
                " / Client:  " + "Dupont" +
                " / Médicaments: ", prescription.toStringForPdf());
    }

    @ParameterizedTest(name="{0} L exception se lève correctement")
    @ValueSource(strings = {"", "    ", "Dy"})
    public void setDoctorLastName_InvalidInput() throws NullPointerException {
        Prescription prescription = new Prescription();
        assertThrows(InputException.class, (Executable) () -> prescription.setDoctorLastName(""));
    }

    /*
    @Test
    public void savePrescriptionAsPdf_ValidInput() throws InputException, IOException {
        Customer customer = new Customer();
        customer.setLastName("Dupont");
        Doctor doctor = new Doctor();
        doctor.setLastName("Ducoin");
        Prescription prescription = new Prescription("01-09-2025", "Ducoin","Dupont");
        prescription.savePrescriptionAsPdf();
        assertEquals("historic\\Dupont2025-09-01.pdf",
                prescription.getPathPdf());
    }
    */

}