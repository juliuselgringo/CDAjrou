package training.afpa.test;

import jdk.jfr.BooleanFlag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.BooleanString;
import training.afpa.model.Book;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    Book book1 = new Book("Book1", "author1", "1235456",1);

    @Test
    public void constructor_GetterAndSetter_ValidInput() {
        assertEquals("Book1", book1.getTitle());
        assertEquals("author1", book1.getAuthor());
        assertEquals("1235456", book1.getIsbn());
        assertEquals(true,book1.getAvailable());
        assertEquals(1,book1.getQuantity());
    }

    @ParameterizedTest(name="{0} le setter leve exception avec succes.")
    @ValueSource(strings={"", "    "})
    public void setTitle_InvalidInput(String title) {
        assertThrows(IllegalArgumentException.class, () -> book1.setTitle(title));
    }

    @ParameterizedTest(name="{0} le setter leve exception avec succes.")
    @ValueSource(strings={"", "    "})
    public void setAuthor_InvalidInput(String author) {
        assertThrows(IllegalArgumentException.class, () -> book1.setAuthor(author));
    }

    @ParameterizedTest(name="{0} le setter leve exception avec succes.")
    @ValueSource(strings={"", "    "})
    public void setIsbn_InvalidInput(String isbn) {
        assertThrows(IllegalArgumentException.class, () -> book1.setIsbn(isbn));
    }


    @ParameterizedTest(name="{0} le setter leve exception avec succes.")
    @ValueSource(ints={-2, -5})
    public void setQuantity_InvalidInput(int quantity) {
        assertThrows(IllegalArgumentException.class, () -> book1.setQuantity(quantity));
    }

    @Test
    public void setQuantity_ZeroInput_AvailableFalse() {
        Book book2 = new Book("Book1", "author1", "1235456",1);
        book2.setQuantity(0);
        assertEquals(false, book2.getAvailable());
    }

}