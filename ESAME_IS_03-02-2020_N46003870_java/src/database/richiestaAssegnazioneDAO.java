package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.sql.PreparedStatement;

import entity.docente;
import entity.tipoElaborato;
import entity.elaborato;
import entity.studente;
//import entity.prefSpec;
import entity.richiestaAssegnazione;
import database.DBManager;
import database.DAOException;
import database.docenteDAO;

public class richiestaAssegnazioneDAO 
{
/*	public static prefSpec creaPref(TreeMap<Integer,elaborato> pref) throws DAOException
	{
		Connection conn = DBManager.getConnection();
		
		if(pref.size() == 1)
		{
			elaborato elab = (elaborato)pref.values();
			
			String sqlquery = "INSERT INTO PreferenzeSpecificate VALUES (NULL,?,null,null);";
			
			try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
			{
				stmt.setInt(1, elab.idElaborato);
				
				stmt.executeUpdate();
			}
			
			catch(SQLException e) 
			{
				
				throw new DAOException("Errore INSERT Elaborato");
			}		
		}
			
	}*/

	public static richiestaAssegnazione create(TreeMap<Integer, elaborato> mapPref, studente stud) throws DAOException 
	{
		richiestaAssegnazione ricAss = new richiestaAssegnazione(mapPref, stud);
		
		String stato = new String("PENDING");
		
		Connection conn = DBManager.getConnection();

		String sqlquery = "INSERT INTO RichiesteAssegnazione VALUES (NULL,?,?,?);";
		
		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setString(1, ricAss.getStudente().getMatricola());
			//stmt.setInt(2, );
			stmt.setString(3, stato);
			
			stmt.executeUpdate();
		}
		
		catch(SQLException e) 
		{
			
			throw new DAOException("Errore INSERT Elaborato");
		}		
		return ricAss;
	}
	
	public static elaborato read(String tematica) throws DAOException 
	{
		
		elaborato elab = null;
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "SELECT TipoElaborato, Insegnamento, Assegnato, Docente FROM Elaborati WHERE Tematica=?";
		
		try ( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			
			stmt.setString(1, tematica.toString());

			try( ResultSet result = stmt.executeQuery(); )
			{
				while (result.next()) 
				{
					tipoElaborato tipo = tipoElaborato.valueOf( result.getString(1) );
					String insegnamento = result.getString(2);
					boolean assegnato = result.getBoolean(3);
					docente doc = docenteDAO.read(result.getString(4));
					
					elab = new elaborato(tematica, tipo, insegnamento, assegnato, doc);
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
		
		String sqlquery = "SELECT Tematica, tipoElaborato, Insegnamento, Assegnato, Docente FROM Elaborati";
		
		try ( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			try( ResultSet result = stmt.executeQuery(); )
			{
				while (result.next()) 
				{   	
					String tematica = result.getString(1);
					tipoElaborato tipo = tipoElaborato.valueOf( result.getString(2) );
					String insegnamento = result.getString(3);
					boolean assegnato = result.getBoolean(4);
					docente doc = docenteDAO.read(result.getString(5));
					
					elaborato elab = new elaborato(tematica, tipo, insegnamento, assegnato, doc);

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
		String tematica = elab.getTematica();
		tipoElaborato tipo = elab.getTipo();
		String insegnamento = elab.getInsegnamento();
		boolean assegnato = elab.getAssegnato();
		docente doc = elab.getDocente();
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "UPDATE Elaborati SET TipoElaborato=?, Insegnamento=?, Assegnato=?, Docente=? WHERE Tematica=?;";

		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setString(1, tipo.toString());
			stmt.setString(2, insegnamento.toString());
			stmt.setBoolean(3, assegnato);
			stmt.setString(4, doc.getID());
			stmt.setString(5, tematica.toString());
			
			stmt.executeUpdate();
		}
		catch(SQLException e) 
		{

			throw new DAOException("Errore UPDATE Elaborato");
		}
	}

	public static void delete(elaborato elab) throws DAOException
	{
		String tematica = elab.getTematica();
		
		Connection conn = DBManager.getConnection();
		
		String sqlquery = "DELETE FROM Elaborati WHERE Tematica=?;";
		
		try( PreparedStatement stmt = conn.prepareStatement(sqlquery); )
		{
			stmt.setString(1, tematica.toString());
			
			stmt.executeUpdate();
		}
		
		catch(SQLException e) 
		{

			throw new DAOException("Errore DELETE Elaborato");
		}
	}
}