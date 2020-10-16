package Metier;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Beans.DemandeDeposer;

public class Demande implements Comparable<Demande>{

	private String jeton;
	private String cinCitoyen;
	private int id_proc;
	private String nomProc;
	private LocalDateTime dateDepo;
	private LocalDateTime dateDebutTraitement;
	private LocalDateTime dateFinTraitement;
	private String etat;
	private ArrayList<String> lienDoc;
	private int idEtapeActuel;
	private Boolean isArchived;
	
	
	
	public Demande() {
		
	}
	public Demande(String jeton, String cinCitoyen, int id_proc, String nomProc, LocalDateTime dateDepo, String etat,
			ArrayList<String> lienDoc, int idEtapeActuel, Boolean isArchived) {
		super();
		this.jeton = jeton;
		this.cinCitoyen = cinCitoyen;
		this.id_proc = id_proc;
		this.nomProc = nomProc;
		this.dateDepo = dateDepo;
		this.etat = etat;
		this.lienDoc = lienDoc;
		this.idEtapeActuel = idEtapeActuel;
		this.isArchived = isArchived;
	}
	
	public Demande(DemandeDeposer d) {
		this.jeton = d.getJeton();
		this.cinCitoyen = d.getCinCitoyen();
		this.etat = d.getEtat();
		this.id_proc = d.getId_proc();
		this.nomProc = d.getNomProc();
		this.lienDoc = d.getLienDoc();
		this.isArchived = false;
		this.dateDepo = LocalDateTime.now();
	}
	public String getJeton() {
		return jeton;
	}
	public void setJeton(String jeton) {
		this.jeton = jeton;
	}
	public String getCinCitoyen() {
		return cinCitoyen;
	}
	public void setCinCitoyen(String cinCitoyen) {
		this.cinCitoyen = cinCitoyen;
	}
	public int getId_proc() {
		return id_proc;
	}
	public void setId_proc(int id_proc) {
		this.id_proc = id_proc;
	}
	public String getNomProc() {
		return nomProc;
	}
	public void setNomProc(String nomProc) {
		this.nomProc = nomProc;
	}
	public LocalDateTime getDateDepo() {
		return dateDepo;
	}
	public void setDateDepo(LocalDateTime dateDepo) {
		this.dateDepo = dateDepo;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public ArrayList<String> getLienDoc() {
		return lienDoc;
	}
	public void setLienDoc(ArrayList<String> lienDoc) {
		this.lienDoc = lienDoc;
	}
	public int getIdEtapeActuel() {
		return idEtapeActuel;
	}
	public void setIdEtapeActuel(int idEtapeActuel) {
		this.idEtapeActuel = idEtapeActuel;
	}
	public Boolean getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
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
	@Override
	public String toString() {
		return "Demande [jeton=" + jeton + ", cinCitoyen=" + cinCitoyen + ", id_proc=" + id_proc + ", nomProc="
				+ nomProc + ", dateDepo=" + dateDepo + ", etat=" + etat + ", lienDoc=" + lienDoc + ", idEtapeActuel="
				+ idEtapeActuel + ", isArchived=" + isArchived + "]";
	}
	@Override
	public int compareTo(Demande o) {
		return this.id_proc - o.getId_proc();
	}
	
	
}
