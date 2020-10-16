package Beans;

public class EtapeForm {

	private String libelle;
	private String description;
	private String resp;
	
	public EtapeForm(String libelle, String description, String resp) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.resp = resp;
	}

	public EtapeForm() {
		super();
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

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}
	
	
}
