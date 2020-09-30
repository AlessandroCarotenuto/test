package entity;

public class elaborato implements Comparable<elaborato>
{
	public int idElaborato;
	public String tematica;
	public tipoElaborato tipo;
	public String insegnamento;
	public boolean assegnato;
	public docente docente;

	public elaborato(String tematica, tipoElaborato tipo, String insegnamento, docente docente)
	{
		super();
		this.idElaborato = -1;
		this.tematica = tematica;
		this.tipo = tipo;
		this.insegnamento = insegnamento;
		this.assegnato = false;
		this.docente = docente;
	}
	
	public elaborato(int idElaborato, String tematica, tipoElaborato tipo, String insegnamento, boolean assegnato, docente docente)
	{
		this.idElaborato = idElaborato;
		this.tematica = tematica;
		this.tipo = tipo;
		this.insegnamento = insegnamento;
		this.assegnato = assegnato;
		this.docente = docente;
	}
	
	public elaborato(String tematica2, tipoElaborato tipo2, String insegnamento2, boolean assegnato2,
			entity.docente doc) {
		// TODO Auto-generated constructor stub
	}

	public void setTematica(String t)
	{
		this.tematica = t;
	}
	
	public String getTematica()
	{
		return tematica;
	}
	
	public void setTipo(tipoElaborato t)
	{
		this.tipo = t;
	}
	
	public tipoElaborato getTipo()
	{
		return tipo;
	}
	
	public void setInsegnamento(String i)
	{
		this.insegnamento = i;
	}
	
	public String getInsegnamento()
	{
		return insegnamento;
	}
	
	public void setAssegnato(boolean a)
	{
		this.assegnato = a;
	}
	
	public boolean getAssegnato()
	{
		return assegnato;
	}

	public void setDocente(docente d)
	{
		this.docente = d;
	}
	
	public docente getDocente()
	{
		return docente;
	}
	
	public int getIdElaborato() 
	{
		return idElaborato;
	}
	
	public void setIdElaborato(int id)
	{
		this.idElaborato = id;
	}

	@Override
	public int compareTo(elaborato e)
	{
	    if(idElaborato > e.idElaborato)
	    {
	    	return 1;
	    }
	    
	    if(idElaborato < e.idElaborato) 
	    {	
	    	return -1;
	    }
	    
	    else 
	    {
	    	return 0;
	    }
	}

	@Override
	public String toString() 
	{
		String s = new String(idElaborato + " " + tematica + " " + insegnamento + " " + tipo + " " + docente.cognome + " " + docente.nome);
		return s;
	}
}