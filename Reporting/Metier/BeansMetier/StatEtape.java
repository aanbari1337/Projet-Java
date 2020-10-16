package BeansMetier;

import java.time.LocalDateTime;

public class StatEtape implements Comparable<StatEtape>{

	private String jeton;
	private int idEtape;
	private LocalDateTime dateDebutTraitement;
	private LocalDateTime dateFinTraitement;
	private String cinEmploye;
	private String etat;
	
	
	public StatEtape() {
		super();
	}
	public StatEtape(String jeton, int idEtape, LocalDateTime dateDebutTraitement, LocalDateTime dateFinTraitement,
			String cinEmploye, String etat) {
		super();
		this.jeton = jeton;
		this.idEtape = idEtape;
		this.dateDebutTraitement = dateDebutTraitement;
		this.dateFinTraitement = dateFinTraitement;
		this.cinEmploye = cinEmploye;
		this.etat = etat;
	}
	public String getJeton() {
		return jeton;
	}
	public void setJeton(String jeton) {
		this.jeton = jeton;
	}
	public int getIdEtape() {
		return idEtape;
	}
	public void setIdEtape(int idEtape) {
		this.idEtape = idEtape;
	}
	public LocalDateTime getDateDebutTraitement() {
		return dateDebutTraitement;
	}
	public void setDateDebutTraitement(LocalDateTime dateDebutTraitement) {
		this.dateDebutTraitement = dateDebutTraitement;
	}
	public LocalDateTime getDateFinTraitement() {
		return dateFinTraitement;
	}
	public void setDateFinTraitement(LocalDateTime dateFinTraitement) {
		this.dateFinTraitement = dateFinTraitement;
	}
	public String getCinEmploye() {
		return cinEmploye;
	}
	public void setCinEmploye(String cinEmploye) {
		this.cinEmploye = cinEmploye;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	@Override
	public String toString() {
		return "StatEtape [jeton=" + jeton + ", idEtape=" + idEtape + ", dateDebutTraitement=" + dateDebutTraitement
				+ ", dateFinTraitement=" + dateFinTraitement + ", cinEmploye=" + cinEmploye + ", etat=" + etat + "]";
	}
	@Override
	public int compareTo(StatEtape arg0) {
		return this.getCinEmploye().compareTo(arg0.getCinEmploye());
	}
	
	
	
}
