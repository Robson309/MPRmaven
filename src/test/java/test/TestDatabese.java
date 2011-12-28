package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;

import org.junit.Test;
import com.pl.robert.*;

import database.PersonManager;

public class TestDatabese {
	
	@Test
	public void TestPersonName() {
		Person person = new Person("Jan", "Niezbedny");
		PersonManager db = new PersonManager();
		db.addPerson(person);
		assertTrue(db.getNameLastAddPerson().equals("Jan"));
	}
	
	@Test
	public void TestPerson() {
		Person person = new Person("Jan", "Niezbedny");
		PersonManager db = new PersonManager();
		db.addPerson(person);
		assertTrue(db.getSurnameLastAddPerson().equals("Niezbedny"));
	}
}
