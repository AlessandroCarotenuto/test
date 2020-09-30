package entity;

public class studente 
{
	private String matricola;
	public int CFU;
	
	public studente(String matricola, int CFU)
	{
		this.matricola = matricola;
		this.CFU = CFU;
	}
	
	public studente(String matricola)
	{
		this.matricola = matricola;
		this.CFU = 0;
	}
	
	public void setMatricola(String m)
	{
		this.matricola = m;
	}

	public String getMatricola()
	{
		return matricola;
	}
	
	public void setCFU(int c)
	{
		this.CFU = c;
	}
	
	public int getCFU()
	{
		return CFU;
	}
}
