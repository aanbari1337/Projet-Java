package Vues;

import java.util.HashMap;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import Controleurs.ControleurStatMoyenTraitement;

public class VueStatMoyenTraitEtape extends JFrame {

	private ControleurStatMoyenTraitement control;
	
	public VueStatMoyenTraitEtape(HashMap<String, Double> Data) {
		setTitle("Moyenne traitement par procedure");
		setSize(800,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		control = new ControleurStatMoyenTraitement();
		
		CategoryDataset dataSet = createDataSet(Data);
		
	    JFreeChart chart=ChartFactory.createBarChart(  
	            "Moyenne duree des traitements par Etape", //Chart Title  
	            "Etapes(Id procedure)", // Category axis  
	            "Moyenne Duree", // Value axis  
	            dataSet,
	            PlotOrientation.VERTICAL, 
	            true,true,false  
	           ); 
	    
	    ChartPanel panel = new ChartPanel(chart);
	    setContentPane(panel);
	}
	
	
	private CategoryDataset createDataSet(HashMap<String, Double> Data) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		for (String str : Data.keySet()) {
			dataSet.addValue(Data.get(str), "Etape", str);
		}
		return dataSet;
	}
	public ControleurStatMoyenTraitement getControl() {
		return control;
	}
	public void setControl(ControleurStatMoyenTraitement control) {
		this.control = control;
	}
	
}
