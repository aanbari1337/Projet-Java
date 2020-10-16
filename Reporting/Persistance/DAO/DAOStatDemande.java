package DAO;

import java.util.ArrayList;
import java.util.HashMap;

import Metier.Demande;

public class DAOStatDemande {

	public static HashMap<String, Integer> getData(ArrayList<Demande> listeDem, String[] nomProc){
		HashMap<String, Integer> data = new HashMap<String, Integer>();
	
		for (int i = 0; i < nomProc.length; i++)
		{
			int count = 0;
			for (int j = 0; j < listeDem.size(); j++) {
				if(listeDem.get(j).getNomProc().equals(nomProc[i]))
					count++;
			}
			data.put(nomProc[i],count);
		}
		
		System.out.println(data);
		
		return data;
	}
}
