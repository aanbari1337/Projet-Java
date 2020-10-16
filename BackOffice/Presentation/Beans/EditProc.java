package Beans;

public class EditProc {

	private String libelle;
	private String chef;
	public EditProc(String libelle, String chef) {
		super();
		this.libelle = libelle;
		this.chef = chef;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getChef() {
		return chef;
	}
	public void setChef(String chef) {
		this.chef = chef;
	}
	
	
}
