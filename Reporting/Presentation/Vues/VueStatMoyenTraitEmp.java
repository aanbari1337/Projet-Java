package Vues;

import java.util.HashMap;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import Controleurs.ControleurStatMoyenTraitement;

public class VueStatMoyenTraitEmp extends JFrame{

	private ControleurStatMoyenTraitement control;
	
	public VueStatMoyenTraitEmp(HashMap<String, Double> Data) {
		
		setTitle("Moyenne traitement par procedure");
		setSize(800,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		control = new ControleurStatMoyenTraitement();
		
		PieDataset dataSet = createDataSet(Data);
		
	    JFreeChart chart=ChartFactory.createPieChart3D(  
	            "Moyenne duree des traitements par Employe", //Chart Title   
	            dataSet,
	            true,true,false 
	           ); 
	    
	    final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );             
	      plot.setStartAngle( 270 );             
	      plot.setForegroundAlpha( 0.60f );             
	      plot.setInteriorGap( 0.02 );
	    
	    ChartPanel panel = new ChartPanel(chart);
	    setContentPane(panel);
	}
	
	
	private PieDataset createDataSet(HashMap<String, Double> Data) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		for (String str : Data.keySet()) {
			dataSet.setValue(str, Data.get(str));
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
