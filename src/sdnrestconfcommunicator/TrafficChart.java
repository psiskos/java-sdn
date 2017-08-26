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
import static sdnrestconfcommunicator.PublicStatics.KEEP_ALIVE_THRESHOLD;
import static sdnrestconfcommunicator.PublicStatics.NO_DRAW_THRESHOLD;
import static sdnrestconfcommunicator.PublicStatics.REFRESH_TIMER;

public class TrafficChart extends JFrame
{
    XYSeries recBytes;
    XYSeries transBytes;
    ChartPanel mChartPanel;
    int counter = 1,noDrawInterval = 1;
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
      double timeInSecs = REFRESH_TIMER * noDrawInterval;
      double rateTr = (trByt - transByt)/timeInSecs;
      double rateRc = (rcByt - recByte)/timeInSecs;
      
      if((rateTr != 0 && rateRc != 0) || noDrawInterval > NO_DRAW_THRESHOLD)
        updateDraw(rateTr,rateRc, trByt, rcByt);
      else
          noDrawInterval++;
          
      //used for drawing x - time axis
      counter++;
    }
   
   public void updateDraw(double rateTrans,double rateRecv, double transmitted, double received)
   {
        transBytes.add(REFRESH_TIMER * counter, rateTrans);
        recBytes.add(REFRESH_TIMER * counter, rateRecv);

        //current bytes value is stored to be substracted from the next value
        transByt = transmitted;
        recByte = received;
        System.out.println("Transmitted: " + transByt + "  "+ rateTrans);
        System.out.println("Received: " +recByte+ "  "+ rateRecv);
        noDrawInterval = 1;
   }
   
   
   private ChartPanel createChartPanel(double trans,double rec) 
   {
        String chartTitle = "Port Traffic";
        String xAxisLabel = "Seconds";
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
