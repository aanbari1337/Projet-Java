package Beans;

public class BeansAjoutEtape {

    private String libelle;
    private String description;
    private String cinEmploye;
	public BeansAjoutEtape(String libelle, String description, String employe) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.cinEmploye = employe;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmploye() {
		return cinEmploye;
	}
	public void setEmploye(String employe) {
		this.cinEmploye = employe;
	}
	@Override
	public String toString() {
		return "BeansAjoutEtape [libelle=" + libelle + ", description=" + description + ", cinEmploye=" + cinEmploye
				+ "]";
	}
    
    
}
