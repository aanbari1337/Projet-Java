package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import BeansMetier.Procedure;

public class DAOProcedure {

	
	
	public static ArrayList<Procedure> listProcedure(){
		
		ArrayList<Procedure> listPrc = new ArrayList<Procedure>();
		
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			String query = "select * from procedur";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Procedure prc = new Procedure();
				prc.setNum(rs.getInt("id_procedure"));
				prc.setLibelle_proc(rs.getString("libelle"));
				prc.setNbr_document(rs.getInt("Nbr_doc"));
				prc.setNbrEtape(rs.getInt("nbr_etape"));
				prc.setIsArchived(rs.getBoolean("isArchived"));
				prc.setDate_cration_proc(rs.getDate("date_creation"));
				prc.setCinEmploye(rs.getString("cin"));
				prc.setModification_proc(rs.getDate("date_modif"));
				prc.setDescription(rs.getString("desc_proc"));
				listPrc.add(prc);
			}
			return listPrc;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int deleteProcedure(int id) {
		
		try {
			Statement st  = (Statement)DAOconnexion.getInstance().getConnexion().createStatement();
			String query = "delete from procedur where id_procedure='"+id+"'";
			int rs = st.executeUpdate(query);
			return rs;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int archiveProcedure(int id) {
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			String query = "update procedur set isArchived=" + 1 +" where id_procedure="+id;
			int result = st.executeUpdate(query);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int addProc(Procedure proc) {
		int result;
		
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			String query = "insert into procedur(id_procedure,libelle,desc_proc,date_creation,date_modif,Nbr_doc,"
					+ "nbr_etape,cin,isArchived) values(" +proc.getNum() +",'"+proc.getLibelle_proc()
					+"','"+proc.getDescription()+"','"+proc.getDate_cration_proc()+"','"+proc.getModification_proc()
					+"',"+proc.getNbr_document()+","+proc.getNbrEtape()+",'"+proc.getCinEmploye()+"',"+0+")";
			
			String query2 =  "insert into procedur(id_procedure,libelle,desc_proc,date_creation,date_modif,Nbr_doc,"
					+ "nbr_etape,isArchived) values('" +proc.getNum() +"','"+proc.getLibelle_proc()
					+"','"+proc.getDescription()+"','"+proc.getDate_cration_proc()+"','"+proc.getModification_proc()
					+"','"+proc.getNbr_document()+"','"+proc.getNbrEtape()+"','"+0+"')";
			
			if(proc.getCinEmploye().equals(""))
				result = st.executeUpdate(query2);
			else
				result = st.executeUpdate(query);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	public static boolean update(Procedure pro, int id_proc) {
		// TODO Auto-generated method stub
		String query = "update procedur set libelle = '"+pro.getLibelle_proc()+"', cin='"+pro.getCinEmploye()
		+"', Nbr_doc = '"+pro.getNbr_document()+"', nbr_etape = '"+pro.getNbrEtape()+"' where id_procedure = '"+id_proc+"'";
			
		String query1 = "update procedur set cin='"+pro.getCinEmploye()
		+"', Nbr_doc = '"+pro.getNbr_document()+"', nbr_etape = '"+pro.getNbrEtape()+"'where id_procedure = '"+id_proc+"'";

		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			int rs;
			if(pro.getLibelle_proc().equals(""))
				rs = st.executeUpdate(query1);
			else
				rs = st.executeUpdate(query);
			if (rs == 0)
				return false;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static Procedure getProcedure(int id){
		// TODO Auto-generated method stub		
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			
			String query = "SELECT * from procedur WHERE id_procedure = '"+id+"'";
			ResultSet rs = st.executeQuery(query);
			rs.next();
			Procedure proc = new Procedure();
			proc.setNum(rs.getInt("id_procedure"));
			proc.setLibelle_proc(rs.getString("libelle"));
			proc.setDescription(rs.getString("desc_proc"));
			proc.setCinEmploye(rs.getString("cin"));
			proc.setNbr_document(rs.getInt("Nbr_doc"));
			proc.setNbrEtape(rs.getInt("nbr_etape"));
			
			return proc;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static boolean nbrOccurenceLibelle(String libelle, int id_proc) {
		String query = "select libelle,id_procedure from procedur";
		int count = 0;
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				if (rs.getString("libelle").equals(libelle) && rs.getInt("id_procedure") != id_proc)
					return false;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
