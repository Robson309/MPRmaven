package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;

import org.junit.Test;
import com.pl.robert.*;

import database.BooksManager;
import database.PersonManager;
import database.PublishersManager;

public class TestDatabese {
	
	@Test
	public void TestPersonName() {
		Person person = new Person("Jan", "Niezbedny");
		PersonManager db = new PersonManager();
		db.addPerson(person);
		assertTrue(db.getNameLastAddPerson().equals("Jan"));
	}
	
	@Test
	public void TestRemovePersons() {
		PersonManager db = new PersonManager();
		db.addPerson(new Person("Jan", "Kowalski"));
		db.addPerson(new Person("Paweł", "Nowak"));
		db.clearAllPerson();
		assertEquals(0, db.getAllPersons().size());
	}
	
	@Test
	public void TestPersonSurname() {
		Person person = new Person("Jan", "Niezbedny");
		PersonManager db = new PersonManager();
		db.addPerson(person);
		assertTrue(db.getSurnameLastAddPerson().equals("Niezbedny"));
	}
	
	@Test
	public void TestRemoveAllPublisher() {
		Publisher helion = new Publisher(1, "helion", "www.helion.pl");
		PublishersManager dbpublisher = new PublishersManager();
		dbpublisher.addPublisher(helion);
		dbpublisher.clearAllPublisher();
		assertEquals(0, dbpublisher.getAllPublisher().size());
	}
		
	@Test
	public void TestAddPublisher() {
		Publisher helion = new Publisher(1, "helion", "www.helion.pl");
		PublishersManager dbpublisher = new PublishersManager();
		dbpublisher.clearAllPublisher(); //usuniecie danych jesli jest jakis wydawca
		dbpublisher.addPublisher(helion);
		assertEquals(1, dbpublisher.getAllPublisher().size());
	}
	
	@Test
	public void TestAddSamePublisher() {
		Publisher helion = new Publisher(1, "helion", "www.helion.pl");
		PublishersManager dbpublisher = new PublishersManager();
		dbpublisher.clearAllPublisher(); //usuniecie danych jesli jest jakis wydawca
		dbpublisher.addPublisher(helion);
		dbpublisher.addPublisher(helion);
		assertEquals(1, dbpublisher.getAllPublisher().size());
	}
	
	@Test
	public void TestAddBook() {
		Book java = new Book("Super kurs Java", "Hostmann", 1, 2007);
		BooksManager dbbooks = new BooksManager();
		dbbooks.clearAllBooks(); //usuniecie danych jesli jest jakis wydawca
		dbbooks.addBook(java);
		assertEquals(1, dbbooks.getAllBooks().size());
	}
	
	@Test
	public void TestRemoveAllBook() {
		Book java = new Book("Super kurs Java", "Hostmann", 1, 2007);
		Book javazaw = new Book("Java zaawansowana", "Cornell", 1, 2009);
		BooksManager dbbooks = new BooksManager();
		dbbooks.addBook(java);
		dbbooks.addBook(javazaw);
		dbbooks.clearAllBooks();
		assertEquals(0, dbbooks.getAllBooks().size());
	}

}
