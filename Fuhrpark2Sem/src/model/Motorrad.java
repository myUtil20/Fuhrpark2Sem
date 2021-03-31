package model;

public class Motorrad extends Fahrzeug {

	private boolean kleinMotorrad;

	public Motorrad() throws FuhrparkException {
		super();
		setKleinMotorrad(false);
	}

	public Motorrad(String name, int alter, int neupreis, boolean kleinMotorrad) throws FuhrparkException {
		super(name, alter, neupreis);
		setKleinMotorrad(kleinMotorrad);
	}
	
	public Motorrad(String csvString) throws FuhrparkException {
		super(csvString);
		String[] werte = csvString.split(";");
		
		if (werte.length >= 5) {
			String typ = werte[0];
			if (!typ.equals("Motorrad")) {
				throw new FuhrparkException("Ungueltiger Typ " + typ + " fuer Motorrad");
			}
			
			String km = werte[4];			
			if (km != null && km.equals("Kleinmotorrad")) {
				setKleinMotorrad(true);
			} else if (km != null && km.equals("Motorrad")) {
				setKleinMotorrad(false);
			} else {
				throw new FuhrparkException("Ungueltiger Wert fuer (Klein-)Motorrad: " + km);
			}
			
		} else {
			throw new FuhrparkException("Ungueltiger csvString: " + csvString);
		}
	}

	@Override
	public String toStringCSV()  {
		String csv = "Motorrad;" + super.toStringCSV();
		
		if (isKleinMotorrad()) {
			csv += ";Kleinmotorrad";
		} else {
			csv += ";Motorrad";
		}
		
		return csv;
	}
	
	public boolean isKleinMotorrad() {
		return kleinMotorrad;
	}

	public void setKleinMotorrad(boolean kleinMotorrad) {
		this.kleinMotorrad = kleinMotorrad;
	}
	
	@Override
	public int versicherung() {
		if (isKleinMotorrad()) {
			return 10;
		} else {
			return 20;
		}
	}

	@Override
	public String toString() {
		String str = super.toString();
		if (isKleinMotorrad()) {
			str += ", Kleinmotorrad";
		} else {
			str += ", Motorrad";
		}
		return str;
	}
	
	
}
