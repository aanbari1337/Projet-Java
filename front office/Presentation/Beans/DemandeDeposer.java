package Beans;

import java.sql.Date;
import java.util.ArrayList;

public class DemandeDeposer {


	private String jeton;
	private String cinCitoyen;
	private int id_proc;
	private String nomProc;
	private java.sql.Date dateDepo;
	private String etat;
	private ArrayList<String> lienDoc;
	private Boolean isArchived;
	
	public DemandeDeposer() {
		
	}
	
	public DemandeDeposer(String jeton, String cinCitoyen, int id_proc, String nomProc, Date dateDepo, String etat,
			ArrayList<String> lienDoc, Boolean isArchived) {
		super();
		this.jeton = jeton;
		this.cinCitoyen = cinCitoyen;
		this.id_proc = id_proc;
		this.nomProc = nomProc;
		this.dateDepo = dateDepo;
		this.etat = etat;
		this.lienDoc = lienDoc;
		this.isArchived = isArchived;
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

	public java.sql.Date getDateDepo() {
		return dateDepo;
	}

	public void setDateDepo(java.sql.Date dateDepo) {
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

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}
	
	
}
