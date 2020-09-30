package entity;

public class docente 
{
	public String nome;
	public String cognome;
	public String ID;
	public int numElaboratiAssegnati;
	
	public docente(String nome, String cognome, String ID, int numElaboratiAssegnati)
	{
		this.nome = nome;
		this.cognome = cognome;
		this.ID = ID;
		this.numElaboratiAssegnati = numElaboratiAssegnati;
	}
	
	public docente(String nome, String cognome, String ID)
	{
		this.nome = nome;
		this.cognome = cognome;
		this.ID = ID;
		this.numElaboratiAssegnati = 0;
	}
	
	public void setNome(String n)
	{
		this.nome = n;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setCognome(String c)
	{
		this.cognome = c;
	}
	
	public String getCognome()
	{
		return cognome;
	}
	
	public void setID(String i)
	{
		this.ID = i;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public void setNumElaboratiAssegnati(int n)
	{
		this.numElaboratiAssegnati = n;
	}
	
	public int getNumElaboratiAssegnati()
	{
		return numElaboratiAssegnati;
	}

}
