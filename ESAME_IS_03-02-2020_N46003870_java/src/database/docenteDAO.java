package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import entity.docente;
import database.DBManager;
import database.DAOException;

public class docenteDAO
{
	public static docente create(String nome, String cognome, String id, int numElaboratiAssegnati) throws DAOException 
	{
		docente doc = new docente(nome, cognome, id, numElaboratiAssegnati);
		
		Connection conn = DBManager.getConnection();

		String sqlquery = "INSERT INTO Docenti VALUES (?, ?, ?, ?);";
		
		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setString(1, doc.getNome());
			stmt.setString(2, doc.getCognome());
			stmt.setString(3, doc.getID());
			stmt.setInt(4, doc.getNumElaboratiAssegnati());
			
			stmt.executeUpdate();
		}
		
		catch(SQLException e) 
		{
			
			throw new DAOException("Errore INSERT Elaborato");
		}
			
		return doc;
	}
	
	public static docente read(String id) throws DAOException 
	{
		
		docente doc = null;
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "SELECT Nome, Cognome, NumeroElaboratiAssegnati FROM Docenti WHERE ID=?";
		
		try ( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			
			stmt.setString(1, id.toString());

			try( ResultSet result = stmt.executeQuery(); )
			{
				while (result.next()) 
				{
					String nome = result.getString(1);
					String cognome = result.getString(2);
					int numElab = result.getInt(3);
					
					doc = new docente(nome, cognome, id, numElab);
				}
			}
		}
		catch(SQLException e) 
		{

			throw new DAOException("Errore SELECT Docente");
		}
		
		return doc;
	}

	public static ArrayList<docente> readAll() throws DAOException 
	{
		
		ArrayList<docente> listaDocenti = new ArrayList<docente>();
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "SELECT Nome, Cognome, ID, NumeroElaboratiAssegnati FROM Docenti";
		
		try ( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			try( ResultSet result = stmt.executeQuery(); )
			{
				while (result.next()) 
				{   	
					String nome = result.getString(1);
					String cognome = result.getString(2);
					String id = result.getString(3);
					int numElab = result.getInt(4);
					
					docente doc = new docente(nome, cognome, id, numElab);

					listaDocenti.add(doc);
				}
			}
		}
		catch(SQLException e) {

			throw new DAOException("Errore SELECT Docenti");
		}
		
		return listaDocenti;
	}
	
	public static void update(docente doc) throws DAOException
	{
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "UPDATE Docenti SET Nome=?, Cognome=?, NumeroElaboratiAssegnati=? WHERE id=?;";

		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setString(1, doc.getNome());
			stmt.setString(2, doc.getCognome());
			stmt.setInt(3, doc.getNumElaboratiAssegnati());
			stmt.setString(4, doc.getID());
			
			stmt.executeUpdate();
		}
		catch(SQLException e) {

			throw new DAOException("Errore UPDATE Docente");
		}
	}

	public static void delete(docente doc) throws DAOException
	{
		Connection conn = DBManager.getConnection();
		
		String id = doc.getID();
		
		String sqlquery = "DELETE FROM Docenti WHERE ID=?;";
		
		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setString(1, id);
			
			stmt.executeUpdate();
		}
		
		catch(SQLException e) 
		{
			throw new DAOException("Errore DELETE Docente");
		}
	}
}	
