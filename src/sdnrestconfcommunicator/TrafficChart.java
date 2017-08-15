/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import static sdnrestconfcommunicator.ControllerUrls.REFRESH_TIMER;

public class TrafficChart extends JFrame
{
    XYSeries recBytes;
    XYSeries transBytes;
    ChartPanel mChartPanel;
    int counter = 1;
    double transByt,recByte;
        
   
   public TrafficChart( double transByt,double recByt)
   {     
        super("Port Traffic Chart");
        this.transByt = transByt;
        this.recByte = recByt;
        mChartPanel = createChartPanel(transByt,recByt);                  
        add(mChartPanel, BorderLayout.CENTER);
  
 
        setSize(500, 500);
        setDefaultCloseOperation(TrafficChart.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);     
   }
   
   public void updateGraph(double trByt,double rcByt) 
   {        
      double rateTr = (trByt - transByt)/REFRESH_TIMER * 1000;
      double rateRc = (rcByt - recByte)/REFRESH_TIMER * 1000;
      recBytes.add(REFRESH_TIMER/1000 * counter, rateRc);
      transBytes.add(REFRESH_TIMER/1000 * counter, rateTr);
      counter++;
      
      //current bytes value is stored to be substracted from the next value
      transByt = trByt;
      recByte = rcByt;
      System.out.println("Transmitted: " + transByt + "  "+ rateTr);
      System.out.println("Received: " +recByte+ "  "+ rateRc);
    }
   
   
   private ChartPanel createChartPanel(double trans,double rec) 
   {
        String chartTitle = "Port Traffic";
        String xAxisLabel = "Time";
        String yAxisLabel = "Bytes/s";

        XYDataset dataset = createDataset(rec,trans);

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return new ChartPanel(chart);
        
    }
 
    private XYDataset createDataset(double rec,double trans) 
    {
        // creates an XY dataset...
            // returns the dataset
        recBytes = new XYSeries("Received");
        transBytes = new XYSeries("Transmitted");
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        //
        recBytes.add(0,0);
        transBytes.add(0,0);
        
        
        dataset.addSeries(recBytes);
        dataset.addSeries(transBytes);

        return dataset;
    }
   
   
   
}
