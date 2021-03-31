package model;

import java.io.*;
import java.util.*;

public class Fuhrpark {
	
	private String name;
	private List<Fahrzeug> fahrzeuge;
	
	public Fuhrpark() throws FuhrparkException {
		setName("n/a");
		fahrzeuge = new ArrayList<>();
	}
	
	public Fuhrpark(String name) throws FuhrparkException {
		setName(name);
		fahrzeuge = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) throws FuhrparkException {
		if (name != null) {
			this.name = name;
		} else {
			throw new FuhrparkException("Ungueltiger Fuhrpark-Name: " + name);
		}
	}
	
	public boolean aufnehmen(Fahrzeug fahrzeug) throws FuhrparkException {
		if (fahrzeug != null) {
			if (enthalten(fahrzeug)) {
				return false;
			} else {
				return fahrzeuge.add(fahrzeug);
			}
		} else {
			throw new FuhrparkException("Fahrzeug ist null");
		}		
	}
	
	public boolean enthalten(Fahrzeug fahrzeug) throws FuhrparkException {
		if (fahrzeug != null) {
			return fahrzeuge.contains(fahrzeug);
		} else {
			throw new FuhrparkException("Fahrzeug ist null");
		}
	}
	
	public boolean entfernen(Fahrzeug fahrzeug) throws FuhrparkException {
		if (fahrzeug != null) {
			return fahrzeuge.remove(fahrzeug);
		} else {
			throw new FuhrparkException("Fahrzeug ist null");
		}
	} 
	
	public List<Fahrzeug> sortiert() {
		List<Fahrzeug> sortierteListe = new ArrayList<>(fahrzeuge);
		Collections.sort(sortierteListe);
		return sortierteListe;
	}
	
	public int gesamtWert() {
		int wert = 0;
		for (Fahrzeug f : fahrzeuge) {
			wert += f.wert();
		}
		return wert;
	}
	
	public String toStringCSV() {
		String csv = getName() + "\n";
		
		for (Fahrzeug f : fahrzeuge) {
			csv += f.toStringCSV() + "\n";
		}
		
		return csv;
	}
	
	public void exportFuhrpark() throws FuhrparkException {
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter("fuhrpark.csv");
			bw = new BufferedWriter(fw);
			
			bw.write(toStringCSV());
			
			bw.close();
			fw.close();
					
		} catch (IOException e) {
			throw new FuhrparkException("Fehler beim Lesen der Datei." + e.getMessage());
		}
		
	}
	
	@SuppressWarnings("resource")
	public void importFuhrpark() throws FuhrparkException {
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader("fuhrpark.csv");
			br = new BufferedReader(fr);
			
			String zeile = br.readLine();
			setName(zeile);
			
			zeile = br.readLine();
			
			while (zeile != null) {
				if (zeile.startsWith("Auto")) {
					aufnehmen(new Auto(zeile));
				} else if (zeile.startsWith("Motorrad")) {
					aufnehmen(new Motorrad(zeile));
				} else {
					throw new FuhrparkException("Unbekannter Fahrzeug-Typ in Zeile: " + zeile);
				}
				
				zeile = br.readLine();
			}			
			
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			throw new FuhrparkException("Datei nicht gefunden.");
		} catch (IOException e) {
			throw new FuhrparkException("Fehler beim Lesen der Datei." + e.getMessage());
		}
		
		
		
		
	}
	
	@Override
	public String toString() {
		String str = "Fuhrpark: " + name + "\n";
		
		for (Fahrzeug f : fahrzeuge) {
			str += f + "\n";
		}
		str += "Gesamtwert: " + gesamtWert() + "\n";
		return str;
	}
	
	
	
}
