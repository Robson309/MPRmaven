package database;

import java.sql.*;
import java.util.*;
import com.pl.robert.*;

public class PublishersManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addPublisherStmt;
	private PreparedStatement getPublisherStmt;
	private PreparedStatement getPublisherByIdStmt;
	private PreparedStatement deletePublisherStmt;

	public PublishersManager() 
	{
		try 
		{
			conn = DriverManager
					.getConnection("jdbc:hsqldb:hsql://localhost/workdb");

			stmt = conn.createStatement();
			boolean publisherTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			while (rs.next()) 
			{
				if ("Publisher".equalsIgnoreCase(rs.getString("TABLE_NAME"))) 
				{
					publisherTableExists = true;
					break;
				}
			}

			if (!publisherTableExists) 
			{
				stmt.executeUpdate(
						"CREATE TABLE publisher("
						+ "id integer,"
						+ "name varchar(25),"
						+ "urlpublisher varchar(40)," 
						+ "UNIQUE (id))");
			}

			addPublisherStmt = conn.prepareStatement("INSERT INTO publisher (id, name, urlpublisher) VALUES (?,?,?)");

			getPublisherStmt = conn.prepareStatement("SELECT * FROM publisher");
			
			getPublisherByIdStmt = conn.prepareStatement("SELECT * FROM publisher WHERE id=?");
			
			deletePublisherStmt = conn.prepareStatement("DELETE FROM publisher");

		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void addPublisher(Publisher publisher) 
	{
		try 
		{
			addPublisherStmt.setInt(1, publisher.getId());
			addPublisherStmt.setString(2, publisher.getName());
			addPublisherStmt.setString(3, publisher.getUrlPublisher());
			addPublisherStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}

	}
	
	public List<Publisher> getAllPublisher()
	{
		List<Publisher> publishers = new ArrayList<Publisher>();
		
		try
		{
			ResultSet rs = getPublisherStmt.executeQuery();
			while (rs.next())
			{
				publishers.add(new Publisher(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return publishers;
	}

	public void getPublisherById(Integer id)
	{
		try
		{
			getPublisherByIdStmt.setInt(1, id);
			ResultSet rs = getPublisherByIdStmt.executeQuery();
			rs.next();
			System.out.println(rs.getString(2) + " " + rs.getString(3));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void clearAllPublisher() 
	{
		try 
		{
			deletePublisherStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
