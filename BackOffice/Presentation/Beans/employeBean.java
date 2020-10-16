package Beans;

public class employeBean {
			
		private String cin;
	    private String grade;
	    private java.sql.Date date_rec;
	    private Boolean sexe;
	    private String nom;
	    private String prenom;
	    private java.sql.Date date_naissance;
	    
		public employeBean() {
			super();
		}

		public String getCin() {
			return cin;
		}

		public void setCin(String cin) {
			this.cin = cin;
		}

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public java.sql.Date getDate_rec() {
			return date_rec;
		}

		public void setDate_rec(java.sql.Date date_rec) {
			this.date_rec = date_rec;
		}

		public Boolean getSexe() {
			return sexe;
		}

		public void setSexe(Boolean sexe) {
			this.sexe = sexe;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public java.sql.Date getDate_naissance() {
			return date_naissance;
		}

		public void setDate_naissance(java.sql.Date date_naissance) {
			this.date_naissance = date_naissance;
		}
}
