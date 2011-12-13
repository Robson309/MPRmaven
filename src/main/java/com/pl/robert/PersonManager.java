package com.pl.robert;

import java.sql.*;
import java.util.*;
import com.pl.robert.*;

public class PersonManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addPersonStmt;
	private PreparedStatement getPersonStmt;
	private PreparedStatement deletePersonStmt;

	public PersonManager() 
	{
		try 
		{
			conn = DriverManager
					.getConnection("jdbc:hsqldb:hsql://localhost/workdb");

			stmt = conn.createStatement();
			boolean personTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			while (rs.next()) 
			{
				if ("Person".equalsIgnoreCase(rs.getString("TABLE_NAME"))) 
				{
					personTableExists = true;
					break;
				}
			}

			if (!personTableExists) 
			{
				stmt.executeUpdate(
						"CREATE TABLE person("
						+ "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
						+ "name varchar(20),"
						+ "surname varchar(30)" + ")");
			}

			addPersonStmt = conn.prepareStatement("INSERT INTO person (name, surname) VALUES (?,?)");

			getPersonStmt = conn.prepareStatement("SELECT * FROM person");
			
			deletePersonStmt = conn.prepareStatement("DELETE FROM person");

		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void addPerson(Person person) 
	{
		try 
		{
			addPersonStmt.setString(1, person.getName());
			addPersonStmt.setString(2, person.getSurname());
			addPersonStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}

	}
	
	public List<Person> getAllPersons()
	{
		List<Person> persons = new ArrayList<Person>();
		
		try
		{
			ResultSet rs = getPersonStmt.executeQuery();
			while (rs.next())
			{
				persons.add(new Person(rs.getString(2), rs.getString(3)));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return persons;
	}

	
	public void clearAllPerson() 
	{
		try 
		{
			deletePersonStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
