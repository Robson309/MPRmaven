package database;

import java.sql.*;
import java.util.*;
import com.pl.robert.*;

public class PersonBooksManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addBookToPersonStmt;
	private PreparedStatement getBookToPersonStmt;
	private PreparedStatement getAllBookToPersonStmt;
	private PreparedStatement deleteBookPersonStmt;
	private PreparedStatement getAllBooksAllPersonsStmt;

	public PersonBooksManager() 
	{
		try 
		{
			conn = DriverManager
					.getConnection("jdbc:hsqldb:hsql://localhost/workdb");

			stmt = conn.createStatement();
			boolean personbookTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			while (rs.next()) 
			{
				if ("PersonBook".equalsIgnoreCase(rs.getString("TABLE_NAME"))) 
				{
					personbookTableExists = true;
					break;
				}
			}

			if (!personbookTableExists) 
			{
				stmt.executeUpdate(
						"CREATE TABLE personbook("
						+ "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
						+ "personid integer,"
						+ "bookid integer);");						
			}

			addBookToPersonStmt = conn.prepareStatement("INSERT INTO personbook (personid, bookid) VALUES (?,?)");

			getBookToPersonStmt = conn.prepareStatement("SELECT * FROM personbook");
			
			getAllBooksAllPersonsStmt = conn.prepareStatement("select name, surname, title, author, datepublication, publisher.name, urlpublisher from person, book, personbook, publisher where personbook.bookid=book.id and book.publisherid=publisher.id and person.id=personbook.personid");
			
			getAllBookToPersonStmt = conn.prepareStatement("SELECT * FROM personbook, person, book where personbook.bookid=book.id and personbook.personid=person.id;");
			
			deleteBookPersonStmt = conn.prepareStatement("DELETE FROM personbook");

		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}
	
	public void addBookToPerson(int bookid, int personid)
	{
		try
		{
			addBookToPersonStmt.setInt(1, personid);
			addBookToPersonStmt.setInt(2, bookid);
			addBookToPersonStmt.executeUpdate();
		}
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}
	
	public void showAllPersonBook()
	{
		try
		{
			ResultSet rs = getAllBookToPersonStmt.executeQuery();
			while (rs.next())
			{
				System.out.println(rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(8));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void showAllPersonsAllBooks()
	{
		try
		{
			ResultSet rs = getAllBooksAllPersonsStmt.executeQuery();
			while (rs.next())
			{
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
