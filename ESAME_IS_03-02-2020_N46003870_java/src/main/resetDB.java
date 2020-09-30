package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DBManager;

public class resetDB 
{
	public static void main(String[] args) 
	{
		try 
		{
		Connection conn = DBManager.getConnection();
		
		String query;
		
		query = "DROP TABLE RichiesteAssegnazione; DROP TABLE PreferenzeSpecificate; DROP TABLE Elaborati; DROP TABLE Docenti; DROP TABLE Studenti";
		
			try(PreparedStatement stmt = conn.prepareStatement(query))
			{
				stmt.executeUpdate();
			}				
			System.out.println("DB removal Successfull.");
		}	
		
		catch(SQLException e) 
		{
			e.printStackTrace();
		}		
	}
}
