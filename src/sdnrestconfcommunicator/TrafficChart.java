/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Font.getFont;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class TrafficChart extends JFrame
{
    
   
   public TrafficChart( int transByt,int recByt, int seconds)
   {
        super("Port Traffic Chart");
        
        JPanel chartPanel = createChartPanel(transByt,recByt,seconds);
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(500, 500);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        
        
   }
   
   private JPanel createChartPanel(int trans,int rec,int seconds) 
   {
        String chartTitle = "Port Traffic";
        String xAxisLabel = "Seconds";
        String yAxisLabel = "Bytes";

        XYDataset dataset = createDataset(rec,trans,seconds);

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return new ChartPanel(chart);
        
    }
 
    private XYDataset createDataset(int rec,int trans,int seconds) 
    {
        // creates an XY dataset...
            // returns the dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries recBytes = new XYSeries("Received");
        XYSeries transBytes = new XYSeries("Transmitted");

        recBytes.add(seconds,rec);
        recBytes.add(seconds*2,rec);

        transBytes.add(seconds,trans);
        transBytes.add(seconds*2,trans);
        
        dataset.addSeries(recBytes);
        dataset.addSeries(transBytes);

        return dataset;
    }
   
   
   
}
