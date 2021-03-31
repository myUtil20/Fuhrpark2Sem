package model;

public class Auto extends Fahrzeug {
	
	private boolean diesel;
	private boolean automatik;
	
	
	public Auto() throws FuhrparkException {
		super();
		setDiesel(false);
		setAutomatik(false);
	}	
	
	public Auto(String name, int alter, int neupreis, boolean diesel, boolean automatik) throws FuhrparkException {
		super(name, alter, neupreis);
		setDiesel(diesel);
		setAutomatik(automatik);
	}
	
	public Auto(String csvString) throws FuhrparkException {
		super(csvString);
		
		if (csvString != null) {
			String[] werte = csvString.split(";");
			// Auto;BMW 325i;10;40000;Benzin;Manuell
			
			if (werte.length >= 6) {
				
				String typ = werte[0];
				if (!typ.equals("Auto")) {
					throw new FuhrparkException("Ungueltiger Typ " + typ + " fuer Auto");
				}
				
				if (werte[4] != null && werte[4].equals("Diesel")) {
					setDiesel(true);
				} else if (werte[4] != null && werte[4].equals("Benzin")) {
					setDiesel(false);
				} else {
					throw new FuhrparkException("Ungueltiger Wert fuer Diesel/Benzin: " + werte[4]);
				}
				
				if (werte[5] != null && werte[5].equals("Automatik")) {
					setAutomatik(true);
				} else if (werte[5] != null && werte[5].equals("Manuell")) {
					setAutomatik(false);
				} else {
					throw new FuhrparkException("Ungueltiger Wert fuer Automatik: " + werte[5]);
				}
			} else {
				throw new FuhrparkException("Ungueltiger csvString: " + csvString  + "; Zu wenige Werte enthalten.");
			}
		} else {
			throw new FuhrparkException("Ungueltiger csvString: " + csvString);
		}
	}
	
	@Override
	public String toStringCSV() {
		
		String csv = "Auto;" + super.toStringCSV();
		if (isDiesel()) {
			csv += ";Diesel";
		} else {
			csv += ";Benzin";
		}
		
		if (isAutomatik()) {
			csv += ";Automatik";
		} else {
			csv += ";Manuell";
		}
		
		return csv;
	}
	
	public boolean isDiesel() {
		return diesel;
	}
	
	public void setDiesel(boolean diesel) {
		this.diesel = diesel;
	}
	
	public boolean isAutomatik() {
		return automatik;
	}
	
	public void setAutomatik(boolean automatik) {
		this.automatik = automatik;
	}
	
	@Override
	public int wert() {
		if (isDiesel()) {
			return Math.max(0, super.wert() - 2000);
		} else {
			return super.wert();
		}
	}
	
	@Override
	public int versicherung() {
		if (isDiesel()) {
			return 60;
		} else {
			return 50;
		}
	}
	
	// Diese Methode ist (laut Angabe) nur in Auto 
	// definiert, und nicht in Fahrzeug
	public int praemie() {
		if (getAlter() <= 10) {
			return 0;
		} else {
			if (isDiesel()) {
				return (int)(0.1 * getNeupreis());
			} else {
				return (int)(0.05 * getNeupreis());
			}
		}
	}

	@Override
	public String toString() {
		String str = super.toString();
		if (isDiesel()) {
			str += ", Diesel";
		} else {
			str += ", Benzin";
		}
		
		if (isAutomatik()) {
			str += ", Automatik";
		} else {
			str += ", Manuell";
		}
		
		return str;
	}
	
	
	
}
