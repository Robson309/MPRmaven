package database;

import java.sql.*;
import java.util.*;

import com.pl.robert.*;

public class BooksManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addBookStmt;
	private PreparedStatement getBookStmt;
	private PreparedStatement deleteBookStmt;
	private PreparedStatement getBookByNameAuthorStmt; 

	public BooksManager() 
	{
		try 
		{
			conn = DriverManager
					.getConnection("jdbc:hsqldb:hsql://localhost/workdb");

			stmt = conn.createStatement();
			boolean bookTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			while (rs.next()) 
			{
				if ("Book".equalsIgnoreCase(rs.getString("TABLE_NAME"))) 
				{
					bookTableExists = true;
					break;
				}
			}

			if (!bookTableExists) 
			{
				stmt.executeUpdate(
						"CREATE TABLE book("
						+ "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
						+ "title varchar(20),"
						+ "author varchar(30),"
						+ "publisherid integer,"
						+ "datepublication integer);");						
			}

			addBookStmt = conn.prepareStatement("INSERT INTO book (title, author, publisherid, datepublication) VALUES (?,?,?,?)");

			getBookStmt = conn.prepareStatement("SELECT * FROM book");
			
			getBookByNameAuthorStmt = conn.prepareStatement("SELECT * FROM book WHERE author=?");
			
			deleteBookStmt = conn.prepareStatement("DELETE FROM book");

		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void addBook(Book book) 
	{
		try 
		{
			addBookStmt.setString(1, book.getTitle());
			addBookStmt.setString(2, book.getAuthor());
			addBookStmt.setInt(3, book.getPublisherId());
			addBookStmt.setInt(4, book.getDatePublication());
			addBookStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}

	}
	
	public List<Book> getAllBooks()
	{
		List<Book> books = new ArrayList<Book>();
		
		try
		{
			ResultSet rs = getBookStmt.executeQuery();
			while (rs.next())
			{
				books.add(new Book(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return books;
	}

	public List<Book> getBookByNameAuthor(String name)
	{
		List<Book> books = new ArrayList<Book>();
		
		try
		{
			getBookByNameAuthorStmt.setString(1, name);
			ResultSet rs = getBookByNameAuthorStmt.executeQuery();
			while (rs.next())
			{
				books.add(new Book(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return books;
	}
	
	public void clearAllBooks() 
	{
		try 
		{
			deleteBookStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
