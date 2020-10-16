package Vues;

import java.util.HashMap;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import Controleurs.ControleurStatDemande;

public class VueStatDemandeTraite extends JFrame{

	private ControleurStatDemande control;
	
	public VueStatDemandeTraite(HashMap<String,Integer> hashMap) {
		setTitle("Nombre de demande traitee pour chaque procedure");
		setSize(800,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		control = new ControleurStatDemande();
		
		CategoryDataset dataSet = createDataSet(hashMap);
		
	    JFreeChart chart=ChartFactory.createBarChart(  
	            "Nombre de demande traitee pour chaque procedure", //Chart Title  
	            "Procedures", // Category axis  
	            "nombre de demande", // Value axis  
	            dataSet,
	            PlotOrientation.VERTICAL, 
	            true,true,false  
	           ); 
	    
	    ChartPanel panel = new ChartPanel(chart);
	    setContentPane(panel);
	}
	
	
	private CategoryDataset createDataSet(HashMap<String,Integer> hashMap) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		
		for (String str : hashMap.keySet()) {
			dataSet.addValue(hashMap.get(str), "Demande", str);
		}
		return dataSet;
	}
	public ControleurStatDemande getControl() {
		return control;
	}
	public void setControl(ControleurStatDemande control) {
		this.control = control;
	}
	
	
}
