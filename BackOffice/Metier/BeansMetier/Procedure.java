package BeansMetier;

import java.sql.Date;

public class Procedure {
	
	private int num;
    private String libelle_proc;
    private java.sql.Date date_cration_proc;
    private java.sql.Date modification_proc;
    private int nbr_document;
    private int nbrEtape;
    private String cinEmploye;
    private String description;
    private Boolean isArchived;


    public Procedure(int num,String libelle_proc, Date date_cration_proc, Date modification_proc, int nbr_document,int nbrEtape,
                    String cinEmploye, String description, Boolean isArchived) {
        this.libelle_proc = libelle_proc;
        this.date_cration_proc = date_cration_proc;
        this.modification_proc = modification_proc;
        this.nbr_document = nbr_document;
        this.cinEmploye = cinEmploye;
        this.description = description;
        this.isArchived = isArchived;
        this.nbrEtape = nbrEtape;
        this.num = num;
    }

    public Procedure() {
    }

    public String getLibelle_proc() {
        return libelle_proc;
    }

    public void setLibelle_proc(String libelle_proc) {
        this.libelle_proc = libelle_proc;
    }

    public Date getDate_cration_proc() {
        return date_cration_proc;
    }

    public void setDate_cration_proc(Date date_cration_proc) {
        this.date_cration_proc = date_cration_proc;
    }

    public Date getModification_proc() {
        return modification_proc;
    }

    public void setModification_proc(Date mofification_proc) {
        this.modification_proc = mofification_proc;
    }

    public int getNbr_document() {
        return nbr_document;
    }

    public void setNbr_document(int nbr_document) {
        this.nbr_document = nbr_document;
    }


    public String getCinEmploye() {
        return cinEmploye;
    }

    public void setCinEmploye(String cinEmploye) {
        this.cinEmploye = cinEmploye;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public int getNbrEtape() {
		return nbrEtape;
	}

	public void setNbrEtape(int nbrEtape) {
		this.nbrEtape = nbrEtape;
	}

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Procedure [num=" + num + ", libelle_proc=" + libelle_proc + ", date_cration_proc=" + date_cration_proc
				+ ", modification_proc=" + modification_proc + ", nbr_document=" + nbr_document + ", nbrEtape="
				+ nbrEtape + ", cinEmploye=" + cinEmploye + ", description=" + description + ", isArchived="
				+ isArchived + "]";
	}
	
}
