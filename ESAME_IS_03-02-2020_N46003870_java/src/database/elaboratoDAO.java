package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import entity.docente;
import entity.tipoElaborato;
import entity.elaborato;
import database.DBManager;
import database.DAOException;
import database.docenteDAO;

public class elaboratoDAO
{
	public static elaborato createElaborato(String tematica, tipoElaborato tipo, String insegnamento, docente docente) throws DAOException 
	{
		elaborato elab = new elaborato(tematica, tipo, insegnamento, docente);
		
		create(elab);
		
		return elab;
	}
	
	public static int create(elaborato elab) throws DAOException
	{				
		Connection conn = DBManager.getConnection();

		int idElab = -1;
		
		String sqlquery = "INSERT INTO Elaborati VALUES (NULL, ?, ?, ?, ?,?);";
		
		try( PreparedStatement stmt = conn.prepareStatement(sqlquery, Statement.RETURN_GENERATED_KEYS); )
		{
			stmt.setString(1, elab.getTematica());
			stmt.setString(2, elab.getTipo().toString());
			stmt.setString(3, elab.getInsegnamento());
			stmt.setBoolean(4, elab.getAssegnato());
			stmt.setString(5, elab.getDocente().getID());
			
			stmt.executeUpdate();
			
			try(ResultSet result = stmt.getGeneratedKeys())
			{
				if(result.next())
				{
					idElab= result.getInt(1);
				}
			}
		}
		
		catch(SQLException e)
		{
			throw new DAOException("Errore INSERT Elaborato");
		}
		
		elab.setIdElaborato(idElab);
		
		return idElab;
	}
	
	public static elaborato read(int id) throws DAOException 
	{
		elaborato elab = null;
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "SELECT Tematica, TipoElaborato, Insegnamento, Assegnato, Docente FROM Elaborati WHERE idElaborato=?";
		
		try ( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{	
			stmt.setInt(1, id);

			try( ResultSet result = stmt.executeQuery(); )
			{
				while (result.next()) 
				{
					String tematica = result.getString(1);
					tipoElaborato tipo = tipoElaborato.valueOf( result.getString(2) );
					String insegnamento = result.getString(3);
					boolean assegnato = result.getBoolean(4);
					docente doc = docenteDAO.read(result.getString(5));
					
					elab = new elaborato(id,tematica, tipo, insegnamento, assegnato, doc);
				}
			}
		}
		
		catch(SQLException e) 
		{

			throw new DAOException("Errore SELECT Elaborato");
		}
		
		return elab;
	}
	
	public static ArrayList<elaborato> readAll() throws DAOException 
	{
		
		ArrayList<elaborato> listaElaborati = new ArrayList<elaborato>();
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "SELECT idElaborato, Tematica, tipoElaborato, Insegnamento, Assegnato, Docente FROM Elaborati";
		
		try ( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			try( ResultSet result = stmt.executeQuery(); )
			{
				while (result.next()) 
				{
					int idElaborato = result.getInt(1);
					String tematica = result.getString(2);
					tipoElaborato tipo = tipoElaborato.valueOf( result.getString(3) );
					String insegnamento = result.getString(4);
					boolean assegnato = result.getBoolean(5);
					docente doc = docenteDAO.read(result.getString(6));
					
					elaborato elab = new elaborato(idElaborato,tematica, tipo, insegnamento, assegnato, doc);

					listaElaborati.add(elab);
				}
			}
		}
		catch(SQLException e) {

			throw new DAOException("Errore SELECT Elaborati");
		}
		
		return listaElaborati;
	}
	
	public static void update(elaborato elab) throws DAOException
	{
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "UPDATE Elaborati SET Tematica=?, TipoElaborato=?, Insegnamento=?, Assegnato=?, Docente=? WHERE idElaborato=?;";

		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setString(1, elab.getTematica());
			stmt.setString(2, elab.getTipo().toString());
			stmt.setString(3, elab.getInsegnamento());
			stmt.setBoolean(4, elab.getAssegnato());
			stmt.setString(5, elab.getDocente().getID());
			stmt.setInt(6, elab.getIdElaborato());
			
			stmt.executeUpdate();
		}
		catch(SQLException e) 
		{

			throw new DAOException("Errore UPDATE Elaborato");
		}
	}

	public static void delete(elaborato elab) throws DAOException
	{		
		Connection conn = DBManager.getConnection();
		
		int idElab = elab.getIdElaborato();
		
		String sqlquery = "DELETE FROM Elaborati WHERE idElaborato=?;";
		
		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setInt(1, idElab);
			
			stmt.executeUpdate();
		}
		catch(SQLException e) 
		{

			throw new DAOException("Errore DELETE Elaborato");
		}
	}

}