package Utils;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import GestionnairesMetier.GestionnaireDoc;
import GestionnairesMetier.GestionnaireEmp;
import GestionnairesMetier.GestionnaireEtape;
import GestionnairesMetier.GestionnaireProc;

public class StoreData {
	
	public static void store() {
		Gson gson = new Gson();
		
		String employes = gson.toJson(GestionnaireEmp.getAllEmployes());
		String procedures = gson.toJson(GestionnaireProc.getAllProc());
		String docs = gson.toJson(GestionnaireDoc.allDoc());
		String etapes = gson.toJson(GestionnaireEtape.getAllEtape());
	
		ArrayList<String> jsonList = new ArrayList<String>();
		jsonList.add(employes);
		jsonList.add(procedures);
		jsonList.add(etapes);
		jsonList.add(docs);
		
		String fileName[] = {"employes.json","procedures.json","etapes.json","docs.json"};
		try {
			File dir = new File("../Data");
			dir.mkdir();
			for(int i =0;i<4;i++) {
				FileWriter file = new FileWriter("../Data/" + fileName[i]);
				file.write(jsonList.get(i));
				file.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
