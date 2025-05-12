package ClassProjectLibrary;

import java.util.Date;

public class Reservation {
    private Date date;
    private Person person;
    private LibraryItem libraryItem;

    public Reservation(Date date, Person person, LibraryItem libraryItem) {
        this.date = date;
        this.person = person;
        this.libraryItem = libraryItem;
    }

    public Date getDate() {
        return date;
    }

    public Person getPerson() {
        return person;
    }

    public LibraryItem getLibraryItem() {
        return libraryItem;
    }
}
