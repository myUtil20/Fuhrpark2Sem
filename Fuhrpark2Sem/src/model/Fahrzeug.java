package model;

public abstract class Fahrzeug implements Comparable<Fahrzeug> {
		
	private String name;
	private int alter;
	private int neupreis;
	
	public Fahrzeug() throws FuhrparkException {
		setName("n/a");
		setAlter(20);
		setNeupreis(10000);
	}
	
	public Fahrzeug(String name, int alter, int neupreis) throws FuhrparkException {
		super();
		setName(name);
		setAlter(alter);
		setNeupreis(neupreis);
	}
	
	public Fahrzeug(String csvString) throws FuhrparkException {
		
		if (csvString != null) {
			
			String[] werte = csvString.split(";");
			
			if (werte.length >= 4) {
				
				setName(werte[1]);
				int alter;
				
				try {
					alter = Integer.parseInt(werte[2]);
					
				} catch (NumberFormatException e) {
					throw new FuhrparkException("Ungueltiger Wert fuer Alter: " + werte[2]);
				}
				setAlter(alter);
				
				int neupreis;
				try {
					neupreis = Integer.parseInt(werte[3]);
				} catch (NumberFormatException e) {
					throw new FuhrparkException("Ungueltiger Wert fuer Neupreis: " + werte[3]);
				}
				
				setNeupreis(neupreis);
				
			} else {
				throw new FuhrparkException("Ungueltiger csvString: " + csvString);
			}
			
		} else {
			throw new FuhrparkException("csvString ist null");
		}
	}
	
	public String toStringCSV() {
		String csv = name + ";" + alter + ";" + neupreis;
		return csv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws FuhrparkException {
		
		if (name != null) {
			this.name = name;
		} else {
			throw new FuhrparkException("Ungueltiger Name: null");
		}
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) throws FuhrparkException {
		if (alter > 0) {
			this.alter = alter;
		} else {
			throw new FuhrparkException("Ungueltiges Alter: " + alter);
		}
	}

	public int getNeupreis() {
		return neupreis;
	}

	public void setNeupreis(int neupreis) throws FuhrparkException {
		if (neupreis > 0) {
			this.neupreis = neupreis;
		} else {
			throw new FuhrparkException("Ungueltiger Neupreis: " + neupreis);
		}
	}
	
	public int wert() {
		return (int)(getNeupreis() * Math.pow(0.9, alter));
	}
	
	public abstract int versicherung();
	
	@Override
	public int compareTo(Fahrzeug other) {
		int comp = this.name.compareTo(other.getName());
		if (comp != 0) {
			comp = this.getAlter() - other.getAlter();
			if (comp != 0) {
				comp = this.getNeupreis() - other.getNeupreis();
			}
		}
		return comp;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alter;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + neupreis;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fahrzeug other = (Fahrzeug) obj;
		if (alter != other.alter)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (neupreis != other.neupreis)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + ", " + getAlter() + " Jahre alt, NP=" + getNeupreis() + ", Wert=" + wert();
	}
	

}
