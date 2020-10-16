package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BeansMetier.Etape;

public class DAOEtape {

	public static boolean add(Etape etape) {
		int rs;
		String query = "INSERT INTO etape (libelle, desc_etape, cin, id_proc)" + 
				"VALUES ('"+etape.getLibelle()+"','"+etape.getDescription()+"','"+etape.getCinEmploye()+"','"+etape.getId_proc()+"')";

		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			rs = st.executeUpdate(query);
			if(rs==0)
				return false;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	public static boolean delete(int id_etape) {
		// TODO Auto-generated method stub
		String query = "delete from etape where (id_etape = '"+id_etape+"')";
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();

			int rs = st.executeUpdate(query);

			if (rs == 0)
				return false;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public static int getId(String libelle){
		// TODO Auto-generated method stub
		String query = "SELECT id_etape from etape WHERE libelle = '"+libelle+"'";
		int id = 0;
		ResultSet rs = null;
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			rs = st.executeQuery(query);
			while(rs.next())
				id = rs.getInt("id_etape");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return id;
	}

	
	public static ArrayList<Etape> getAll(int id_proc) {
		// TODO Auto-generated method stub
		ArrayList<Etape> listEtape = new ArrayList<Etape>();
		String query = "SELECT * from etape where id_proc = '"+id_proc+"'";
		ResultSet rs=null;
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Etape etp = new Etape();
				etp.setId_etape(rs.getInt("id_etape"));
				etp.setLibelle(rs.getString("libelle"));
				etp.setDescription(rs.getString("desc_etape"));
				etp.setCinEmploye(rs.getString("cin"));
				etp.setId_proc(rs.getInt("id_proc"));
				
				listEtape.add(etp);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return listEtape;
	}

	
	public static boolean update(Etape etape, int id_etape) {
		// TODO Auto-generated method stub
		String query = "update etape set libelle = '"+etape.getLibelle()+"',desc_etape ='"+etape.getDescription()
		+"',cin = '"+etape.getCinEmploye()+"'where id_etape = '"+id_etape+"'";
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();

			int rs = st.executeUpdate(query);
			if (rs == 0)
				return false;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	
	public static Etape searchById(int id) {
		// TODO Auto-generated method stub
		String query = "SELECT * from etape WHERE id_etape = '"+id+"'";
		Etape etp = new Etape();
		ResultSet rs = null;
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();

			rs = st.executeQuery(query);
			rs.next();
			etp.setId_etape(rs.getInt("id_etape"));
			etp.setLibelle(rs.getString("libelle"));
			etp.setDescription(rs.getString("desc_etape"));
			etp.setCinEmploye(rs.getString("cin"));
			etp.setId_proc(rs.getInt("id_proc"));
			
			return etp;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	public static String[] getCINEmploye() {
		// TODO Auto-generated method stub
		ArrayList<String> lisCIN = new ArrayList<String>();
		String query = "SELECT cin from employe";
		ResultSet rs = null;
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();

			rs = st.executeQuery(query);
			while(rs.next()) {
				lisCIN.add(rs.getString("cin"));
			}
			String array[] = new String[lisCIN.size()];
			
			array = lisCIN.toArray(array);
			return array;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	public static ArrayList<Etape> getAllEtape() {
		// TODO Auto-generated method stub
		ArrayList<Etape> listEtape = new ArrayList<Etape>();
		String query = "SELECT * from etape";
		ResultSet rs=null;
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();

			rs = st.executeQuery(query);
			while(rs.next()) {
				Etape etp = new Etape();
				etp.setId_etape(rs.getInt("id_etape"));
				etp.setLibelle(rs.getString("libelle"));
				etp.setDescription(rs.getString("desc_etape"));
				etp.setCinEmploye(rs.getString("cin"));
				etp.setId_proc(rs.getInt("id_proc"));
				
				listEtape.add(etp);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return listEtape;
	}
	
	public static boolean nbrOccurenceLibelle(String libelle, int id_etape) {
		String query = "select libelle,id_etape from etape";
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				if (rs.getString("libelle").equals(libelle) && rs.getInt("id_etape") != id_etape)
					return false;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
