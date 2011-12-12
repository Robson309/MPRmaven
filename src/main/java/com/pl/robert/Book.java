package com.pl.robert;

public class Book {
		
		public String title;
		public String author;
		public Integer datepublication;
		
		public Book(String title, String author, Integer datepublication)
		{
			this.title = title;
			this.author = author;
			this.datepublication = datepublication;
		}
		
		public void showBooks()
		{
			System.out.println("Title: "+ title + " author: "+ author + " date of publication: " + datepublication);
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}
		
		public Integer getDatePublication() {
			return datepublication;
		}

		public void setDatePublication(Integer datepublication) throws YearException
		{
			if (datepublication < 1900)
			{
					throw new YearException("Year publication can not be after 1900");
			}
			else this.datepublication=datepublication;
		}
	}