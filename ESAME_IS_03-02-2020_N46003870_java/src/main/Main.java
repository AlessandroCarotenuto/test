package main;

import entity.*;
import database.DAOException;
import control.*;

import java.util.TreeMap;

public class Main 
{
	public static void main(String[] args)
	{
		try
		{
			GestioneElaborati ges_elab = new GestioneElaborati();
			GestioneSedute ges_sed = new GestioneSedute();
			
			//Crea e aggiunge nuovi docenti
			docente d1 = ges_sed.addDocente("Mario", "Rossi", "NA01", 12);
			docente d2 = ges_sed.addDocente("Luisa", "Verdi", "NA02", 3);
			docente d3 = ges_sed.addDocente("Roberto", "Neri", "NA03", 11);
			
			//Crea e aggiunge uno studente
			studente s = ges_sed.addStudente("N47002620", 135);
			
			//Crea nuovi elaborati
			elaborato e1 = new elaborato("Java", tipoElaborato.COMPILATIVO, "P1", d1);
			elaborato e2 = new elaborato("Linux", tipoElaborato.PROGETTO, "SO", d2);
			elaborato e3 = new elaborato("JVM", tipoElaborato.COMPILATIVO, "P2", d1);
			elaborato e4 = new elaborato("JDBC", tipoElaborato.PROGETTO, "BD", d3);
			elaborato e5 = new elaborato("OOP", tipoElaborato.COMPILATIVO, "P1", d2);
			elaborato e6 = new elaborato("Protocolli Internet", tipoElaborato.PROGETTO, "RC", d2);
			elaborato e7 = new elaborato("Java", tipoElaborato.COMPILATIVO, "P1", d3);
			elaborato e8 = new elaborato("Python", tipoElaborato.PROGETTO, "P2", d3);
			
			/*Carica gli elaborati*/
			ges_elab.addElaborato(e1);
			ges_elab.addElaborato(e2);
			ges_elab.addElaborato(e3);
			ges_elab.addElaborato(e4);
			ges_elab.addElaborato(e5);
			ges_elab.addElaborato(e6);
			ges_elab.addElaborato(e7);
			ges_elab.addElaborato(e8);
			
			//Crea una Richiesta di Assegnazione
			TreeMap<Integer,elaborato> t = new TreeMap<Integer,elaborato>();
			
			int x = 0;
					
			t.put(x,e1);
			t.put(x++,e2);
			t.put(x++,e3);
			
			richiestaAssegnazione r = new richiestaAssegnazione(t,s);
			//richiestaAssegnazione r = creaRichiesta(s,);
			
			//Effettua l'assegnazione
			ges_elab.assegnaElaborato(r, 130);
			
			/*Elimina gli oggeti creati
			ges_elab.delElaborato(e1);
			ges_elab.delElaborato(e2);
			ges_elab.delElaborato(e3);
			ges_elab.delElaborato(e4);
			ges_elab.delElaborato(e5);
			ges_elab.delElaborato(e6);
			ges_elab.delElaborato(e7);
			ges_elab.delElaborato(e8);
			
			ges_sed.delDocente(d1);
			ges_sed.delDocente(d2);
			ges_sed.delDocente(d3);
			
			ges_sed.delStudente(s);
			}*/
		}
		catch (DAOException e)
		{
			System.out.println("ERRORE: Impossibile accedere al database.\n");

			e.printStackTrace();

			System.exit(1);
		}
	}
}
