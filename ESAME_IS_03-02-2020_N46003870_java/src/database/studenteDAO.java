package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import entity.studente;
import database.DBManager;
import database.DAOException;

public class studenteDAO
{
	public static studente create(String matricola, int CFU) throws DAOException 
	{
		studente stud = new studente(matricola, CFU);
		
		Connection conn = DBManager.getConnection();

		String sqlquery = "INSERT INTO Studenti VALUES (?, ?);";
		
		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setString(1, stud.getMatricola());
			stmt.setInt(2, stud.getCFU());

			stmt.executeUpdate();
		}
		
		catch(SQLException e) 
		{
			
			throw new DAOException("Errore INSERT Studente");
		}
		
		return stud;
	}
	
	public static studente read(String matricola) throws DAOException 
	{
		
		studente stud = null;
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "SELECT CFU FROM Studenti WHERE Matricola=?";
		
		try ( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			
			stmt.setString(1, matricola.toString());

			try( ResultSet result = stmt.executeQuery(); )
			{
				while (result.next()) 
				{
					int CFU = result.getInt(1);
					
					stud = new studente(matricola, CFU);
				}
			}
		}
		catch(SQLException e) 
		{

			throw new DAOException("Errore SELECT Studente");
		}
		
		return stud;
	}

	public static ArrayList<studente> readAll() throws DAOException 
	{
		
		ArrayList<studente> listaStudenti = new ArrayList<studente>();
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "SELECT Matricola, CFU FROM Studenti";
		
		try ( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			try( ResultSet result = stmt.executeQuery(); )
			{
				while (result.next()) 
				{   	
					String matricola = result.getString(1);
					int CFU = result.getInt(2);
					
					studente stud = new studente(matricola, CFU);

					listaStudenti.add(stud);
				}
			}
		}
		catch(SQLException e) {

			throw new DAOException("Errore SELECT Studenti");
		}
		
		return listaStudenti;
	}

	public static void update(studente stud) throws DAOException
	{		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "UPDATE Studenti SET CFU=? WHERE Matricola=?;";

		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setInt(1, stud.getCFU());
			stmt.setString(2, stud.getMatricola());
			
			stmt.executeUpdate();
		}
		catch(SQLException e) 
		{

			throw new DAOException("Errore UPDATE Studente");
		}
	}

	public static void delete(studente stud) throws DAOException
	{
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "DELETE FROM Studenti WHERE Matricola=?;";
		
		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setString(1, stud.getMatricola());
			
			stmt.executeUpdate();
		}
		catch(SQLException e) 
		{

			throw new DAOException("Errore DELETE Studente");
		}
	}
}
