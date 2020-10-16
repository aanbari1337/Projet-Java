package BeansMetier;

import java.sql.Date;

public class Employe {

    private String cin;
    private String mdp_emp;
    private String grade;
    private java.sql.Date date_rec;
    private Boolean sexe;
    private String nom;
    private String prenom;
    private java.sql.Date date_naissance;
    private Boolean isArchived;

    public Employe(String cin, String mdp_emp, String grade, Date date_rec, Boolean sexe,
                   String nom, String prenom, Date date_naissance, Boolean isArchived) {
        this.cin = cin;
        this.mdp_emp = mdp_emp;
        this.grade = grade;
        this.date_rec = date_rec;
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.isArchived = isArchived;
    }

    public Employe() {
    }

    public String getCin_emp() {
        return cin;
    }

    public void setCin_emp(String cin) {
        this.cin = cin;
    }

    public String getMdp_emp() {
        return mdp_emp;
    }

    public void setMdp_emp(String mdp_emp) {
        this.mdp_emp = mdp_emp;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(Date date_rec) {
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

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }
}
