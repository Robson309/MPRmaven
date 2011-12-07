package com.pl.robert;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Person {
	private static Logger logger = Logger.getLogger(Person.class);
	
	public long id;
	public String name;
	public String surname;
		
	public List<Book> bookList = new ArrayList<Book>();
	 
	public Person() {}
	
	public Person(String name, String surname)
	{
		this.name = name;
		this.surname = surname;
	}
	
	public Person(String name, String surname, List<Book> bookList)
	{
		this.name = name;
		this.surname = surname;
		this.bookList = bookList;
	}
	
	public void showBooks()
	{
		for(Book b : this.bookList)
		{
			b.showBooks();
		}
	}

	public void showPerson() {
		System.out.println(name + " " + surname);
		logger.info("show person " + name + " " + surname );
	}

	public void printBook() {
		for (Book b : bookList) {
			b.showBooks();
		}
	}

	public void addBook(Book b) {
		
		bookList.add(b);
		logger.info("Book: " + b.title + " author " + b.author + " date of publication " + b.datepublication);
	}

	public void removeBook(Book book) {		
		bookList.remove(book);
		logger.info("remove book" + book.title);
		}
		

	public void clearBookList() {
		bookList.clear();
		logger.info("Clear book list");
	}


	public Book searchBookTitle(String title) {
		
		for (Book book : bookList) {
			if (book.getTitle().equals(title)) {
				return book;
			}
		}
		logger.info("search book" + title);
		return null;
	}
	
	public Book findBookAuthor(String author) {
		
		for (Book book : bookList) {
			if (book.getAuthor().equals(author)) {
				return book;
			}
		}
		logger.info("search book by author" + author);
		return null;
	}
	
	public List<Book> findAll(String title) {
		List<Book> resultsList= new ArrayList<Book>();
		for (Book book : bookList) {
			if (book.getTitle().equals(title)) {
				resultsList.add(book);
			}
		}
		logger.info("findAll by title: " + title);
		return resultsList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
