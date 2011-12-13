package com.pl.robert;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import com.pl.robert.PersonManager;

public class Main {
	
	private static Logger logger= Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("Log4J.properties");
		logger.info("start program");
		
		Person person1 = new Person ("Adam", "Nowak");
		Person person2 = new Person ("Marian", "Jakis");
			
		Book java = new Book("Java","Horstan", 2005);
		Book games = new Book("Creation games","Demo", 1998);
		Book C = new Book("C#","Rader", 1999);
		Book computer = new Book("How works computer","unknow autor", 2006);
		
		try {
			java.setDatePublication(1840);
			} catch (YearException e) {
			logger.error(e);
			
		}
		
		games.setTitle("Creation games 2");
		List<Book> bookList = new ArrayList<Book>();
		Person person = new Person("Jan", "Kowalski", bookList);
		
		person.addBook(java);
		person.addBook(games);
		person.addBook(C);
		person.addBook(computer);
		person.removeBook(person.searchBookTitle("Java"));
		
		person.showPerson();
		person.showBooks();
		logger.info("finish program");
		
		PersonManager db = new PersonManager();
		db.addPerson(person1);
		db.addPerson(person2);
		System.out.println("sadf");
		
		for (Person persons : db.getAllPersons())
		{
			System.out.println(persons.getName()
					+ " " + persons.getSurname());
		}
		
		Book superbook = new Book("Superbook","Koles", 2008);
		Book notsuberbook = new Book("Notsuperbook","Kolo", 2010);
		BooksManager dbbooks = new BooksManager();
		dbbooks.addBook(superbook);
		dbbooks.addBook(notsuberbook);
		//db.clearAllPerson();
	}
}

