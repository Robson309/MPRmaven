package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;

import org.junit.Test;
import com.pl.robert.*;

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
}
