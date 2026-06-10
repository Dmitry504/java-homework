package edu.phystech.hw2.contact;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public record Contact(String username, String email) implements Comparable<Contact>{
    public final String UNKNOWN_EMAIL = "example@gmail.com";

    public Contact(String username){
        this(username, UNKNOWN_EMAIL)
    }

    public Contact{
        if (username == null || username.trim().isEmpty()){
            throw new InvalidContactFieldException("username");
        }

        if (email == null || email.trim().isEmpty()){
            throw new InvalidContactFieldException("email");
        }
    }

    public int compareTo(Contact other){
        return Integer.compare(this.username.lenght(), other.username.lenght())
    }
}

public class ContactTest {

    @Test
    public void justWorks() {

        Assertions.assertDoesNotThrow(() -> {
            Contact first = new Contact("username", "username@gmail.com");
            Contact second = new Contact("123", "123@gmail.com");

            Contact withoutEmail = new Contact("withoutEmail");
            Assertions.assertEquals(Contact.UNKNOWN_EMAIL, withoutEmail.email());
        });

    }

    @Test
    public void validationTest() {
        var exception =
                Assertions.assertThrows(InvalidContactFieldException.class, () -> new Contact("   ", "123@gmai.com"));
        Assertions.assertEquals("username", exception.getFieldName());
        exception =
                Assertions.assertThrows(InvalidContactFieldException.class, () -> new Contact("   1", "123@mai.ru"));
        Assertions.assertEquals("email", exception.getFieldName());
        exception = Assertions.assertThrows(InvalidContactFieldException.class, () -> new Contact("   ", ""));
        Assertions.assertEquals("username", exception.getFieldName());
    }

    @Test
    public void compareTest() {
        var result = Stream.of(new Contact("AFD"), new Contact("a"), new Contact("zZ")).sorted(Contact::compareTo)
                .map(Contact::username).toList();
        Assertions.assertEquals(List.of("a", "zZ", "AFD"), result);
        Assertions.assertInstanceOf(Comparable.class, new Contact("AFD"));
    }
}
