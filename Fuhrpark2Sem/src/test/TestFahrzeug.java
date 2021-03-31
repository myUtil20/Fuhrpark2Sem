package test;

import model.*;

public class TestFahrzeug {
	
	public static void main(String[] args) {

		System.out.println("Start TestFahrzeug");
		
		Fahrzeug f = null;
		
		try {
			f = new Auto("VW Golf", 15, 22000, true, true);
			// System.out.println(f.toStringCSV());
			
			f = new Motorrad("Ducati", 10, 13900, false);
			// System.out.println(f.toStringCSV());
			
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			f = new Auto("Auto;VW Golf;15;32000;Diesel;Manuell");
			System.out.println(f);
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
			
		try {
			f = new Auto("Auto;VW Golf;fuenfzehn;32000;Diesel;Manuell");
			System.out.println(f);
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}	

		try {
			f = new Auto("Auto;VW Golf;;32000;Diesel;Manuell");
			System.out.println(f);
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			f = new Auto("Auto;VW Golf;32000;Diesel;Manuell");
			System.out.println(f);
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			f = new Auto("Auto;VW Golf;15;32000;Diesel");
			System.out.println(f);
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			f = new Auto("Auto;VW Golf;15;32000;Diesel;Manuel");
			System.out.println(f);
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			f = new Auto("AUTO;VW Golf;15;32000;Diesel;Manuell");
			System.out.println(f);
		} catch (FuhrparkException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
}
