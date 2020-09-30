package control;

import database.DAOException;
import entity.*;
import database.studenteDAO;
import database.docenteDAO;

import java.util.ArrayList;

public class GestioneSedute
{

	private ArrayList<studente> listaStudenti = new ArrayList<studente>();
	private ArrayList<docente> listaDocenti = new ArrayList<docente>();
	
	public GestioneSedute() throws DAOException 
	{
		listaStudenti = studenteDAO.readAll();
	}
	
	public ArrayList<studente> getStudenti() 
	{
		return listaStudenti;
	}

	public void setStudenti(ArrayList<studente> l)
	{
		this.listaStudenti = l;
	}
	
	public studente addStudente(String matricola, int CFU) throws DAOException 
	{
		studente s = studenteDAO.create(matricola,CFU);
		listaStudenti.add(s);
		
		return s;
	}
	
	public docente addDocente(String nome, String cognome, String id, int numElabAss) throws DAOException 
	{
		docente d = docenteDAO.create(nome,cognome,id,numElabAss);
		listaDocenti.add(d);
		
		return d;
	}
	
	public void delDocente(docente d) throws DAOException
	{
		docenteDAO.delete(d);
		listaDocenti.remove(d);
	}
	
	public void delStudente(studente s) throws DAOException
	{
		studenteDAO.delete(s);
		listaStudenti.remove(s);
	}
}
