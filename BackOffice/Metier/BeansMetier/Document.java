package BeansMetier;

public class Document {

    private String libelle_doc;
    private String descriptionDoc;
    private int idProc;
    private int idDoc;


    public Document(String libelle_doc, String descriptionDoc,int idProc) {
 
        this.libelle_doc = libelle_doc;
        this.descriptionDoc = descriptionDoc;
        this.idProc= idProc;
    }

    public Document() {
    }

    public String getLibelle_doc() {
        return libelle_doc;
    }

    public void setLibelle_doc(String libelle_doc) {
        this.libelle_doc = libelle_doc;
    }
    
    


    public String getDescriptionDoc() {
        return descriptionDoc;
    }

    public void setDescriptionDoc(String descriptionDoc) {
        this.descriptionDoc = descriptionDoc;
    }

	public int getIdProc() {
		return idProc;
	}

	public void setIdProc(int idProc) {
		this.idProc = idProc;
	}

	@Override
	public String toString() {
		return "Document [libelle_doc=" + libelle_doc + ", descriptionDoc=" + descriptionDoc + ", idProc=" + idProc
				+ "]";
	}

	public int getIdDoc() {
		return idDoc;
	}

	public void setIdDoc(int idDoc) {
		this.idDoc = idDoc;
	}
	
	
}
