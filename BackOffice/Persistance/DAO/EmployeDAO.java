package DAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import BeansMetier.Employe;

public class EmployeDAO extends DAOEmployeAbstract {
	
	
	public EmployeDAO() {}

	public static int addEmp(Employe employe){
		
		int result;
		try {
			employe.setMdp_emp("mdp");
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		String query = "insert into employe values('"+employe.getCin_emp()+"','"+employe.getMdp_emp()+"','"+employe.getDate_rec()+
				"','"+employe.getGrade()+"','"+employe.getNom()+"','"+employe.getPrenom()+"','"+employe.getDate_naissance()+"',"
				+employe.getSexe()+","+employe.getArchived()+")";
		result = st.executeUpdate(query);
		return result;}
		catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int delEmp(String cin){
		
		int result;
		
		try{
			Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
			String query = "delete from employe where cin='"+cin+"'";
			result = st.executeUpdate(query);
			return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static ArrayList<Employe> getAllEmployes(){
		
		ArrayList<Employe> employes = new ArrayList<Employe>();
		try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		String query = "select *from employe";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			Employe employe = new Employe();
			
			employe.setArchived(rs.getBoolean(9));
			employe.setGrade(rs.getString(4));
			employe.setNom(rs.getString(5));
			employe.setPrenom(rs.getString(6));
			employe.setDate_naissance(rs.getDate(7));
			employe.setDate_rec(rs.getDate(3));
			employe.setSexe(rs.getBoolean(8));
			employe.setCin_emp(rs.getString(1));
			employe.setMdp_emp(rs.getString(2));
			
			employes.add(employe);
		}
		return employes;}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Employe getEmploye(String cin){
		
		Employe employe = new Employe();
		ResultSet rs;
		try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		String query = "select *from where cin='"+cin+"'";
		rs = st.executeQuery(query);
		
		employe.setArchived(rs.getBoolean(9));
		employe.setNom(rs.getString(5));
		employe.setPrenom(rs.getString(6));
		employe.setDate_naissance(rs.getDate(7));
		employe.setDate_rec(rs.getDate(3));
		employe.setSexe(rs.getBoolean(8));
		employe.setCin_emp(rs.getString(1));
		employe.setMdp_emp(rs.getString(2));
		
		return employe;}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int updateEmp(Employe employe, String cin,int gradechanged){
		
		int result;
		try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		System.out.println(employe.getGrade());
		if (gradechanged == 0)
			result = st.executeUpdate("update procedur set cin=NULL where cin='"+cin+"'");
		String query = "update employe set date_rec='"+employe.getDate_rec()+"',Grade='"+employe.getGrade()+
				"',Nom='"+employe.getNom()+"',Prenom='"+employe.getPrenom()+"',date_naissance='"+employe.getDate_naissance()+
				"',sexe="+employe.getSexe()+",isArchived="+employe.getArchived()+",cin='"+employe.getCin_emp()+"' where cin='"+cin+"'";

		result = st.executeUpdate(query);
		return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int existEmp(String cin){
		
		ResultSet rs = null;
		try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		String query = "select *from employe where cin='"+cin+"'";
		rs = st.executeQuery(query);
		if (!rs.next())
			return 0;
		return 1;}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static int archiverEmp(String cin) {
		
		int result;
		String query = "update employe set isArchived=true where cin='"+cin+"'";
		try {
			Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
			result = st.executeUpdate(query);
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
public static String[] getChef(){
		
		ArrayList<String> chefs = new ArrayList<String>();
		try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		String query = "select cin,Nom,Prenom from employe where grade='Chef de division'";
		
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			chefs.add(rs.getString("cin"));
		}
		String ch[]= new String[chefs.size()];
		ch = chefs.toArray(ch);
		return ch;
		
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String[] getEmployes(){
		
		ArrayList<String> chefs = new ArrayList<String>();
		try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		String query = "select cin from employe";
		
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			chefs.add(rs.getString("cin"));
		}
		String ch[]= new String[chefs.size()];
		ch = chefs.toArray(ch);
		return ch;
		
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
