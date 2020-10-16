package UtilClass;

public class Statistiques {

	private String libelle;
	private int nbV;
	private int nbR;
	private int nbT;
	public Statistiques(String libelle, int nbV, int nbR, int nbT) {
		super();
		this.libelle = libelle;
		this.nbV = nbV;
		this.nbR = nbR;
		this.nbT = nbT;
	}
	public Statistiques() {
		super();
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getNbV() {
		return nbV;
	}
	public void setNbV(int nbV) {
		this.nbV = nbV;
	}
	public int getNbR() {
		return nbR;
	}
	public void setNbR(int nbR) {
		this.nbR = nbR;
	}
	public int getNbT() {
		return nbT;
	}
	public void setNbT(int nbT) {
		this.nbT = nbT;
	}
	@Override
	public String toString() {
		return "Statistiques [libelle=" + libelle + ", nbV=" + nbV + ", nbR=" + nbR + ", nbT=" + nbT + "]";
	}
	
	
	
}
