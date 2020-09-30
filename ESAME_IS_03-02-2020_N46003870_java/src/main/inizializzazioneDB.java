package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBManager;

public class inizializzazioneDB 
{

	public static void main(String[] args) 
	{
		Connection conn = DBManager.getConnection();
		
		ArrayList<String> sqlqueries = new ArrayList<String>();
		
		sqlqueries.add("CREATE TABLE Studenti\n" +
				"  ( \n" + 
				"  Matricola VARCHAR(10) NOT NULL,\n" + 
				"  CFU INT UNSIGNED NOT NULL,\n" +  
				"  PRIMARY KEY(Matricola)\n" + 
				");");
		
		sqlqueries.add("CREATE TABLE Docenti( \n" + 
				"  Nome VARCHAR(100) NOT NULL,\n" +
				"  Cognome VARCHAR(100) NOT NULL,\n" +
				"  ID VARCHAR(8) NOT NULL,\n" +
				"  NumeroElaboratiAssegnati INT UNSIGNED NOT NULL,\n" + 
				"  PRIMARY KEY(ID)\n" + 
				");");
		
		sqlqueries.add("CREATE TABLE Elaborati\n" +
				"  (\n" + 
				"  idElaborato INT NOT NULL AUTO_INCREMENT,\n" +
				"  Tematica VARCHAR(200) NOT NULL,\n" +
				"  TipoElaborato VARCHAR(100) NOT NULL,\n" +
				"  Insegnamento VARCHAR(200) NOT NULL,\n" +
				"  Assegnato BOOLEAN NOT NULL,\n" + 
				"  Docente VARCHAR(200) NOT NULL,\n" +
				"  FOREIGN KEY(Docente) REFERENCES Docenti(ID),\n" +
				"  PRIMARY KEY(idElaborato)\n" + 
				");");
		
		sqlqueries.add("CREATE TABLE PreferenzeSpecificate\n" +
					"(\n" +
					"idPrefSet int not null auto_increment primary key,\n" +
					"Preferenza1 int not null,\n" +
					"foreign key(Preferenza1) references Elaborati(idElaborato),\n" +
					"Preferenza2 int,\n" +
					"foreign key(Preferenza2) references Elaborati(idElaborato),\n" +
					"Preferenza3 int,\n" +
					"foreign key(Preferenza3) references Elaborati(idElaborato)\n" + 
					");");
		
		sqlqueries.add("CREATE TABLE RichiesteAssegnazione\n" +
						"(\n" +
						"idRichiesta INT NOT NULL AUTO_INCREMENT,\n" +
						"Studente VARCHAR(100) NOT NULL,\n" +
						"FOREIGN KEY(Studente) REFERENCES Studenti(Matricola),\n" +
						"idPrefSpec INT NOT NULL,\n" +
						"FOREIGN KEY(idPrefSpec) REFERENCES PreferenzeSpecificate(idPrefSet),\n" +
						"StatoRichiesta VARCHAR(50) NOT NULL,\n" +
						"PRIMARY KEY(idRichiesta)\n" +
						");");
		try 
		{
			for(String query : sqlqueries) 
			{
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.executeUpdate();
			}
			
			System.out.println("DB Initialization Successful.");
		}
		
		catch(SQLException e) 
		{

			e.printStackTrace();
		}	
	}
}