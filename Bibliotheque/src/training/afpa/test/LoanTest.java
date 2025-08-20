package training.afpa.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import training.afpa.model.Book;
import training.afpa.model.Loan;
import training.afpa.model.Subscriber;
import training.afpa.vue.Display;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    @Test
    public void constructor_GetterAndSetter_ValidInputs() {
        Book book1 = new Book("Book1", "author1", "1235456",10);
        Subscriber subscriber1 = new Subscriber("Adele",
                "Boulanger",
                "adele.boulanger@afpa.training");
        Loan loan10 = new Loan("adele.boulanger@afpa.training","Book1");
        assertEquals(LocalDate.now(), loan10.getLoanDate());
        assertEquals(LocalDate.now().plusDays(7), loan10.getReturnDate());
        assertEquals("adele.boulanger@afpa.training", loan10.getSubscriber().getEmail());
        assertEquals("Book1", loan10.getBook().getTitle());
    }

}