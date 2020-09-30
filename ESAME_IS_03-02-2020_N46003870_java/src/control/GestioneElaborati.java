package control;

import database.DAOException;
import entity.*;
import database.elaboratoDAO;
import database.richiestaAssegnazioneDAO;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class GestioneElaborati
{
	private ArrayList<elaborato> listaElaborati = new ArrayList<elaborato>();
	private ArrayList<richiestaAssegnazione> listaRichieste = new ArrayList<richiestaAssegnazione>();
	
	public GestioneElaborati() throws DAOException 
	{
		listaElaborati = elaboratoDAO.readAll();
	}
	
	public ArrayList<elaborato> getElaborati() 
	{
		return listaElaborati;
	}

	public void setElaborati(ArrayList<elaborato> l)
	{
		this.listaElaborati = l;
	}
	
	public void addElaborato(elaborato e) throws DAOException 
	{
		listaElaborati.add(e);
	
		int idElab = elaboratoDAO.create(e);
		
		e.setIdElaborato(idElab);			
	}
	
	public void delElaborato(elaborato e) throws DAOException
	{
		elaboratoDAO.delete(e);
		listaElaborati.remove(e);
	}
	
	public int assegnaElaborato(richiestaAssegnazione r, int CFUmin) throws DAOException
	{	
		elaborato elab = null;
		
		int CFUstu = r.studente.CFU; 
		
		if(CFUstu >= CFUmin)
		{
			TreeMap<Integer,elaborato> preferenzeMap = new TreeMap<Integer,elaborato>();
			
			preferenzeMap = r.getPreferenze();
    		    
		    for(Iterator<Entry<Integer, elaborato>> it = preferenzeMap.entrySet().iterator(); it.hasNext();) 
		    {
		    	Map.Entry a = (Map.Entry)it.next();
		    	elaborato e = (elaborato) a.getValue();
		    	
		    	if(!e.assegnato)
		    	{		    		
		    		if (e.docente.numElaboratiAssegnati <= 10)
		    		{
		    			//Caso in cui viene assegnato l'elaborato richiesto
		    			
		    			elab = elaboratoDAO.read(e.idElaborato);
		    			
		    			System.out.println("NUMERO CFU RICHIESTO: " + CFUmin + "\n" +
								   		   "NUMERO CFU POSSEDUTI: " + CFUstu + "\n\n" +
								           "NUMERO SUFFICIENTE PER ASSEGNAZIONE ELABORATO\n\n" +
								           "L'elaborato che ti è stato assegnato è:\n" + elab.toString());    			
		    			return 2;
		    		}
		    	}
		    	
		    }

		    //Caso in cui gli elaborati richiesti non sono disponibili
				    
		    Iterator<elaborato> iter = listaElaborati.iterator();
		    
		    while(iter.hasNext())
		    {
		    	
		    elaborato el = iter.next();
		    	
		    	if(el.assegnato == false && el.docente.numElaboratiAssegnati <= 10)
		    	{	
		    	elab = elaboratoDAO.read(el.idElaborato);	
		    	
		    	System.out.println("NUMERO CFU RICHIESTO: " + CFUmin + "\n" +
		    			           "NUMERO CFU POSSEDUTI: " + CFUstu + "\n\n" +
			                       "NUMERO SUFFICIENTE PER ASSEGNAZIONE ELABORATO\n\n" +
			   		               "Non è stato possibile assegnare uno degli elaborati richiesti. Ti è stato automaticamente assegnato l'elaborato:\n\n" + elab.toString());   			
    			return 1;
		    	}
		    }
	    	
		    //Caso in cui non ci sono elaborati disponibili nemmeno tra i rimanenti
		    
		    System.out.println("NUMERO CFU RICHIESTO: " + CFUmin + "\n" +
	    					   "NUMERO CFU POSSEDUTI: " + CFUstu + "\n\n" +
                               "NUMERO SUFFICIENTE PER ASSEGNAZIONE ELABORATO\n\n" +
		                       "Non è stato possibile assegnare uno degli elaborati richiesti. Né sono stati trovati elaborati disponibili.");
		    
		    return 0;
		}
		
		//Caso in cui il numero di CFU non è sufficiente
		
		else
		{
			System.out.println("NUMERO CFU RICHIESTO: " + CFUmin + "\n" +
					   		   "NUMERO CFU POSSEDUTI: " + CFUstu + "\n\n" +
					           "NUMERO INSUFFICIENTE PER ASSEGNAZIONE ELABORATO");
		return -1;
		}
	}	

	public richiestaAssegnazione creaRichiesta(studente s, TreeMap<Integer,elaborato> t) throws DAOException
	{
		
		
		richiestaAssegnazioneDAO.create(t,s);
		
		//TreeMap<Integer,elaborato> t = new TreeMap<Integer,elaborato>();
		
		richiestaAssegnazione r = new richiestaAssegnazione(t,s);
		
		listaRichieste.add(r);
		
		return r;
	}
}