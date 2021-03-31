package test;

import model.*;

public class TestFuhrpark {
	
	public static Fuhrpark fp;
	
	public static void main(String[] args) {
		System.out.println("--- Start Test Fuhrpark ---");
		testFuellen();
		System.out.println("--- Test Export Fuhrpark ---");
		testExport();
		
		System.out.println("--- Test Import Fuhrpark ---");
		try {
			fp = new Fuhrpark(); // altes Fuhrpark-Objekt "loeschen", neue Instanz erzeugen
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
		testImport();
		System.out.println("Fuhrpark nach (Re-)Import: " + fp);
	
	}


	public static void testFuellen() {
		
		try {
			fp = new Fuhrpark("Meine Autos");
			fp.aufnehmen(new Auto("BMW 325i", 10, 40000, false, false));
			fp.aufnehmen(new Auto("Audi 80", 15, 35000, false, true));
			fp.aufnehmen(new Auto("VW Golf", 25, 27000, true, false));
			fp.aufnehmen(new Auto("VW Golf", 25, 27000, true, false));
			fp.aufnehmen(new Motorrad("Ducati", 22, 29000, false));
			fp.aufnehmen(new Motorrad("Vespa", 8, 12000, true));
			//fp.aufnehmen(new Fahrzeug("Fiaker", 40, 31000));
			
			System.out.println(fp);
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void testImport() {
		try {
			fp.importFuhrpark();
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void testExport() {
		try {
			fp.exportFuhrpark();
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
	}
}
