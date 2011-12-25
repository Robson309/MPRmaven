package test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.pl.robert.*;

import database.PersonManager;

public class TestDatabese {
	
	@Test
	public void TestPersonName() {
		Person person = new Person("Jan", "Niezbedny");
		PersonManager db = new PersonManager();
		db.addPerson(person);
		assertTrue(person.getName().equals("Jan"));
	}
}
