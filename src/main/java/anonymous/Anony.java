package anonymous;

import java.sql.*;
import java.util.*;
import com.pl.robert.*;
import anonymous.*;

public class Anony {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement showBooksStmt;

	public Anony() 
	{
		try 
		{
			conn = DriverManager
					.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			stmt = conn.createStatement();
			showBooksStmt = conn.prepareStatement("SELECT title, author, datepublication FROM book");
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}
	
	public void ShowAllBooksByDate(Condition condit)
	{		
		try
		{
			ResultSet rs = showBooksStmt.executeQuery();
			while (rs.next())
			{
				if (condit.getConndition(rs.getInt(3)))
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getInt(3));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
