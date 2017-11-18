/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import static sdnrestconfcommunicator.PublicStatics.CONGESTION_THRESHOLD;
import static sdnrestconfcommunicator.PublicStatics.NO_DRAW_THRESHOLD;
import static sdnrestconfcommunicator.PublicStatics.GRAPH_REFRESH_TIME;

public class TrafficChart extends JFrame
{
    XYSeries recBytes;
    XYSeries transBytes;
    ChartPanel mChartPanel;
    JFreeChart chart;
    int counter = 1,noDrawInterval = 1,interfaceSpeed;
    double transByt,recByte,linkUtilTr,linkUtilRc;
        
   
   public TrafficChart( double transByt,double recByt, int interfaceSpeed)
   {     
        super("Port Traffic Chart");
        this.interfaceSpeed = interfaceSpeed;
        this.transByt = transByt;
        this.recByte = recByt;
        mChartPanel = createChartPanel(transByt,recByt);                  
        add(mChartPanel, BorderLayout.CENTER);
  
 
        setSize(500, 500);
        setDefaultCloseOperation(TrafficChart.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);     
   }
   
   public void updateGraph(double trByt,double rcByt,int interfaceSpeed) 
   {       
      double timeInSecs = GRAPH_REFRESH_TIME * noDrawInterval;
      double rateTr = (trByt - transByt)/timeInSecs;
      double rateRc = (rcByt - recByte)/timeInSecs;
      
      
      if((rateTr != 0 && rateRc != 0) || noDrawInterval > NO_DRAW_THRESHOLD)
      {
        updateDraw(rateTr,rateRc, trByt, rcByt);
//        linkUtilTr = 8 * rateTr/interfaceSpeed / 10;//int speed is in kbps not bps
//        System.out.println("Outgoing Link Utilization " + linkUtilTr + " %");
//        linkUtilRc = 8 * rateRc/interfaceSpeed / 10;
//        System.out.println("Incoming Link Utilization " + linkUtilRc + " %");

      }
      else
          noDrawInterval++;
          
      //used for drawing x - time axis
      counter++;
    }
   
   public void updateDraw(double rateTrans,double rateRecv, double transmitted, double received)
   {
        transBytes.add(GRAPH_REFRESH_TIME * counter, rateTrans);
        recBytes.add(GRAPH_REFRESH_TIME * counter, rateRecv);

        //current bytes value is stored to be substracted from the next value
        transByt = transmitted;
        recByte = received;
        System.out.println("Transmitted: " + transByt + "  "+ rateTrans);
        System.out.println("Received: " +recByte+ "  "+ rateRecv);
        noDrawInterval = 1;
   }
   
   
   private ChartPanel createChartPanel(double trans,double rec) 
   {
        int speedInMega = interfaceSpeed/1000;
        String chartTitle = "Capacity: " + speedInMega + " Mbps";
        String xAxisLabel = "Seconds";
        String yAxisLabel = "Bytes/s";

        XYDataset dataset = createDataset(rec,trans);

        chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);
        addMarker();
        return new ChartPanel(chart);
        
    }
   
    private void addMarker()
    {
        XYPlot plot = chart.getXYPlot();
        double markerPoint = interfaceSpeed / 8 * CONGESTION_THRESHOLD * 10;//intspeed is in kbps
        Marker congestion = new ValueMarker(markerPoint);
        congestion.setPaint(Color.red);
        congestion.setLabel("Link Congestion Threshold: " + CONGESTION_THRESHOLD + " %");
        congestion.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        congestion.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(congestion);
    }
 
    private XYDataset createDataset(double rec,double trans) 
    {
        recBytes = new XYSeries("Received");
        transBytes = new XYSeries("Transmitted");
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        recBytes.add(0,0);
        transBytes.add(0,0);
               
        dataset.addSeries(recBytes);
        dataset.addSeries(transBytes);

        return dataset;
    }
   
   
   
}
