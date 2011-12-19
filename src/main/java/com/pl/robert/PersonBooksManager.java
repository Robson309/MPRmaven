package com.pl.robert;

import java.sql.*;
import java.util.*;
import com.pl.robert.*;

public class PersonBooksManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addBookToPersonStmt;
	private PreparedStatement getBookToPersonStmt;
	private PreparedStatement deleteBookPersonStmt;

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
			
			deleteBookPersonStmt = conn.prepareStatement("DELETE FROM personbook");

		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

}
