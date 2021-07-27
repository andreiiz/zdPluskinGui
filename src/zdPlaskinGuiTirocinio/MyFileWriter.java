package zdPlaskinGuiTirocinio;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFileWriter {
	
	public static void createResultFile(String durata, String timeStep, String saveTime, String tempCostanteString, String tempInizialeString, String tempFinaleString, String taoString, int tipoTemperatura, String plasmaTempString) {
		
		//uso PrintWriter invece che BufferedWriter perchè è più comodo il ritorno a capo.
		PrintWriter prova = null;
		try {
			
			prova = new PrintWriter(new FileWriter("result.txt"));
			
		}catch(IOException e) {
			MyPanel.setLabell("Errore nella creazione del file");
		}
		if(tipoTemperatura == 1) {
			prova.println(1);  //1 se la temperatura è costante
			prova.println(durata);
			prova.println(timeStep);
			prova.println(saveTime);
			
		}else if(tipoTemperatura == 0) {
			prova.println(0);  //zero se è variabile
			prova.println(durata);
			prova.println(timeStep);
			prova.println(saveTime);
		}
			if(tempCostanteString != null && (tempInizialeString == null) &&  (tempFinaleString ==  null) && taoString == null) {	//se costante..
				
				prova.println(tempCostanteString);
				prova.println(plasmaTempString);
				
				prova.close();
				
				
			}else if((tempCostanteString == null ) && (tempInizialeString != null) &&  (tempFinaleString !=  null) && (taoString != null)) { //se variabile..
				
				prova.println(tempInizialeString);

				prova.println(tempFinaleString);
			
				prova.println(taoString);
				
				prova.println(plasmaTempString);
				
				prova.flush();
				
				prova.close();
				
			
			}
			
		
	}
	
	public static void createPathFile(File path)  {
		
		FileReader reader;
		PrintWriter r ;
		BufferedReader lettura;
		try {
		 reader = new FileReader(path);
		 r = null;
		 lettura = new BufferedReader(reader);
		r = new PrintWriter(new FileWriter("inputFile.txt"));
		String riga = null;
		
			 riga = lettura.readLine();
			while( (riga != null)) {
				r.println(riga);
				riga = lettura.readLine();
			}
			
			r.flush();
			r.close(); 	//se si omette il close non scrive nulla sul file
			
		}catch(IOException e) {
			MyPanel.setLabell("Errore nella creazione del file");
			 
		}
		
		
		
	}
	
}
