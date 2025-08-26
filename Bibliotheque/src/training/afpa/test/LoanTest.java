package training.afpa.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import training.afpa.model.Book;
import training.afpa.model.Loan;
import training.afpa.model.Subscriber;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    Book book1 = new Book("Book1", "author1", "1235456",10);
    Subscriber subscriber1 = new Subscriber("Adele",
            "Boulanger",
            "adele.boulanger@afpa.training");
    Loan loan10 = new Loan("adele.boulanger@afpa.training","Book1");

    @Test
    public void constructor_GetterAndSetter_ValidInputs() {
        assertEquals(LocalDate.now(), loan10.getLoanDate());
        assertEquals(LocalDate.now().plusDays(7), loan10.getReturnDate());
        assertEquals("adele.boulanger@afpa.training", loan10.getSubscriber().getEmail());
        assertEquals("Book1", loan10.getBook().getTitle());
    }

    @Test
    public void setReturnDate_ValidInputs() throws Exception {
        loan10.setReturnDate(LocalDate.now().plusDays(10));
        assertEquals(LocalDate.now().plusDays(10), loan10.getReturnDate());
    }

    @ParameterizedTest(name="{0} la methode leve l exception")
    @ValueSource(strings = {"2000-10-01"})
    public void setReturnDate_InvalidInputs() throws Exception {
        assertThrows(Exception.class, () -> loan10.setReturnDate(LocalDate.now().minusDays(5)));
    }

    @Test
    public void toString_ValidInputs(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        assertEquals("pret{Date du pret: " + LocalDate.now().format(formatter) + ", Date de retour: " +
                        LocalDate.now().plusDays(7).format(formatter) +
                        ", Titre: Book1, Emprunteur: adele.boulanger@afpa.training}\n",
                        loan10.toString());
    }



}