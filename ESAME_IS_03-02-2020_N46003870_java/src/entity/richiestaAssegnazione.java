package entity;

import java.util.TreeMap;

public class richiestaAssegnazione 
{
	private int codice;
	public TreeMap<Integer,elaborato> preferenzeSpecificate;
	public statoRichiesta stato;
	public studente studente;
	
	public richiestaAssegnazione(TreeMap<Integer,elaborato> preferenzeSpecificate, studente studente)
	{
		this.preferenzeSpecificate = preferenzeSpecificate; 
		this.stato = statoRichiesta.PENDING;
		this.codice = -1;
		this.studente = studente;
	}
	
	public statoRichiesta getStato()
	{
		return stato;
	}
	
	public void setStato(statoRichiesta s)
	{
		this.stato = s;
	}
	
	public int getCodice()
	{
		return codice;
	}
	
	public void setCodice(int c)
	{
		this.codice = c;
	}
	
	public TreeMap<Integer,elaborato> getPreferenze()
	{
		return preferenzeSpecificate;
	}
	
	public void setPreferenze(TreeMap<Integer,elaborato> p)
	{
		this.preferenzeSpecificate = p;
	}
	
	public studente getStudente()
	{
		return studente;
	}
	
	public void setStudente(studente s)
	{
		this.studente = s;
	}
	
}
