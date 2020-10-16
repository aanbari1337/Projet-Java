package Controleurs;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import GestionnairesMetier.GestionnaireStat;
import UtilClass.Statistiques;
import Vues.VueStatDemProc;

public class ControleurStatDemProc {

	private VueStatDemProc vue;
	public ControleurStatDemProc(VueStatDemProc vue) {
		this.vue = vue;
		
	}
	
	public void showStatByProc() {
		CategoryDataset data= createDataset();
		if(data==null) {
			JOptionPane.showMessageDialog(null, "Erreur veuillez ressayer","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JFreeChart barChart = ChartFactory.createBarChart(
	    		 "Taux d'acceptation des demandes par procedure",           
	         "Procedure",            
	         "taux d'acceptation/Refus",            
	         data,          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
		 ChartPanel chartPanel = new ChartPanel( barChart );
		 vue.getPanel().removeAll();
		 vue.getPanel().add(chartPanel);
		 vue.getPanel().validate();
		
		
	}
	
	public void showStatAllProc() {
		PieDataset pie = createDatasetall();
		if(pie ==null) {
			JOptionPane.showMessageDialog(null, "Erreur veuillez ressayer","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		JFreeChart chart = ChartFactory.createPieChart3D(
				"Taux d'acceptation de demandes par plusieurs procedures",
	            pie,
	            true,
	            true,
	            false
	        );
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.5f);
        ChartPanel chartPanel = new ChartPanel( chart );
		 vue.getPanel().removeAll();
		 vue.getPanel().add(chartPanel);
		 vue.getPanel().validate();
	}
	private CategoryDataset createDataset() {
		DefaultCategoryDataset dataset =new DefaultCategoryDataset( );  
		ArrayList<Statistiques> list = GestionnaireStat.statProc();
		if(list ==null)
			return null;
		for (int i = 0;i<list.size();i++) {
			dataset.addValue(list.get(i).getNbV(), "ACCEPTEE", list.get(i).getLibelle());
			dataset.addValue(list.get(i).getNbR(), "REJETEE", list.get(i).getLibelle());
		}
		return dataset;
	}

	private PieDataset createDatasetall() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<Statistiques> list = GestionnaireStat.statProc();
		if(list ==null)
			return null;
		for(int i = 0;i<list.size();i++) {
			dataset.setValue(list.get(i).getLibelle(),
						(list.get(i).getNbV() * 100)/list.get(i).getNbT());
		}
		return dataset;
	}
}
