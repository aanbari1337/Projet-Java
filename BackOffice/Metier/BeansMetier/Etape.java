package BeansMetier;

public class Etape implements Comparable<Etape> {

    private int id_etape;
	private String libelle;
    private String description;
    private String cinEmploye;
    private int id_proc;

    public Etape(int id_etape,String libelle, String description, String employe,int id_proc) {
        this.id_etape = id_etape;
    	this.libelle = libelle;
        this.description = description;
        this.cinEmploye = employe;
        this.id_proc = id_proc;

    }
    
    public Etape() {
		super();
	}


    public String getLibelle() {
        return libelle;
    }

    public String getDescription() {
        return description;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getCinEmploye() {
		return cinEmploye;
	}

	public void setCinEmploye(String cinEmploye) {
		this.cinEmploye = cinEmploye;
	}

	public int getId_etape() {
		return id_etape;
	}

	public void setId_etape(int id_etape) {
		this.id_etape = id_etape;
	}

	public int getId_proc() {
		return id_proc;
	}

	public void setId_proc(int id_proc) {
		this.id_proc = id_proc;
	}

	@Override
	public String toString() {
		return "Etape [id_etape=" + id_etape + ", libelle=" + libelle + ", description=" + description + ", cinEmploye="
				+ cinEmploye + ", id_proc=" + id_proc + "]";
	}

	@Override
	public int compareTo(Etape o) {
		return this.id_etape - o.getId_etape();
	}



    
	
}
