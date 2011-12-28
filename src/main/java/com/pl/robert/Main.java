package com.pl.robert;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;


import database.*;

public class Main {
	
	private static Logger logger= Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("Log4J.properties");
		logger.info("start program");
		
		Person person1 = new Person ("Adam", "Nowak");
		Person person2 = new Person ("Marian", "Jakis");
		
		Publisher helion = new Publisher(1, "helion", "www.helion.pl"); 
			
		Book java = new Book("Java","Horstan", 1, 2005);
		Book games = new Book("Creation games","Demo", 1, 1998);
		Book C = new Book("C#","Rader", 1, 1999);
		Book computer = new Book("How works computer","unknow autor", 1, 2006);
		
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
		
		//*******************************************
		System.out.println("*******************************************");
		PersonManager db = new PersonManager();
		db.addPerson(person1);
		db.addPerson(person2);
		System.out.println("sadf");
		
		for (Person persons : db.getAllPersons())
		{
			System.out.println(persons.getName()
					+ " " + persons.getSurname());
		}
		
		Book superbook = new Book("Superbook", "Koles", 1, 2008);
		Book notsuberbook = new Book("Notsuperbook", "Kolo", 1, 2010);
		BooksManager dbbooks = new BooksManager();
		dbbooks.addBook(superbook);
		dbbooks.addBook(notsuberbook);
		
		for (Book books : dbbooks.getAllBooks())
		{
			System.out.println(books.getTitle()
					+ " "+ books.getAuthor());
		}
		
		PersonBooksManager dbpersonbook = new PersonBooksManager();
		
		dbpersonbook.addBookToPerson(1, 1);
		dbpersonbook.showAllPersonBook();
		
		PublishersManager dbpublisher = new PublishersManager();
		dbpublisher.addPublisher(helion);
		for (Publisher publishers : dbpublisher.getAllPublisher())
		{
			System.out.println(publishers.getId()
					+ " " + publishers.getName()
					+ " " + publishers.getUrlPublisher());
		}
		
		//db.clearAllPerson();
		//dbbooks.clearAllBooks();
	}
}

