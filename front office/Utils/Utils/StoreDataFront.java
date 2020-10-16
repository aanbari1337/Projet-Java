package Utils;

import java.io.IOException;


public class StoreDataFront {

	public StoreDataFront() {
		
		exportData();
	}
	
	private void exportData() {
		String[] str= {"demande","StatEtape"};
		for (int i = 0; i < str.length; i++) {
			try {
				Runtime.getRuntime().exec("mongoexport --jsonArray -d front -c "+str[i]+" -o ../Data/"+str[i]+".json");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
