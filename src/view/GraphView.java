package view;

import csv.Country;
import csv.IndicatorType;
import csv.LinkedList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.DataModel;

import java.util.Iterator;
import java.util.Scanner;

public class GraphView extends LineChart<Number,Number>
{
   
   NumberAxis xAxis;
   NumberAxis yAxis;
   DataModel model;
   IndicatorType indicator;
   
   public GraphView(DataModel model, IndicatorType type)
   {
      super(new NumberAxis(), new NumberAxis());
      
      this.model = model;
      indicator = type;
      xAxis = (NumberAxis) getXAxis();
      yAxis = (NumberAxis) getYAxis();
      
      xAxis.setLabel(model.getXAxisName());
      yAxis.setLabel(indicator.getLabel());
   }
   
   public Series[] seriesFromCountry(Country currentCountry)
   {
      Series[] seriesArray = new Series[2];
      
      XYChart.Series<Number, Number> series = new XYChart.Series<>();
      series.setName(currentCountry.getName());
      
      int linkedListSize = (currentCountry.getEndYear() - currentCountry.getStartYear()) + 1;
      int startYear = currentCountry.getStartYear();
      
      if (indicator == IndicatorType.SCHOOL_ENROLLMENT)
      {
         // Create a new Series for Secondary Data.
         XYChart.Series<Number, Number> series02 = new XYChart.Series<>();
         
         // Change Country names to include the Indicator data type.
         series.setName(
                 (new StringBuilder(currentCountry.getName()).append(" \"Primary School Enrollment\"")).toString());
         series02.setName(
                 (new StringBuilder(currentCountry.getName()).append(" \"Secondary School Enrollment\"")).toString());
         
         // Fill the series
         for (int index = 0; index < linkedListSize; index++)
         {
            // Primary Data
            series.getData().add(new XYChart.Data<Number, Number>(
                    currentCountry.getIndicatorForYear((startYear + index)).getYear(),
                    currentCountry.getIndicatorForYear(startYear + index).getData()[0]));
            
            // Secondary Data
            series02.getData().add(new XYChart.Data<Number, Number>(
                    currentCountry.getIndicatorForYear((startYear + index)).getYear(),
                    currentCountry.getIndicatorForYear(startYear + index).getData()[1]));
         }
         
         seriesArray[0] = series;
         seriesArray[1] = series02;
   
         return seriesArray;
      }
      
      // If data is not School enrollment, fill one series
      for (int index = 0; index < linkedListSize; index++)
      {
         series.getData().add(new XYChart.Data<Number, Number>(
                 currentCountry.getIndicatorForYear((startYear + index)).getYear(),
                 currentCountry.getIndicatorForYear(startYear + index).getData()[0]));
      }
      seriesArray[0] = series;
      
      return seriesArray;
   }
   
   public void update()
   {
      // Ask how many countries the user wants to graph.
      int selection = -1;
      Scanner keyBoard = new Scanner(System.in);
      while(true)
      {
         String userRequest = "";
         try
         {
            System.out.println("\nHow many countries would you like to graph? (Choose a number between 1 - 8).");
            userRequest = keyBoard.nextLine();
            selection = Integer.parseInt(userRequest);
            if (selection >= 1 && selection <= 8)
               break;
         }
         catch (NumberFormatException ne)
         {
            System.out.printf("Invalid input %s. ", userRequest);
         }
      }
   
      // Find a # of random countries.
      CountrySelector countrySelector = new CountrySelector(model.getModel(), selection);
      
      LinkedList<Country> countryLinkedList = countrySelector.selectCountries();
      
      Iterator<Country> itr = countryLinkedList.iterator();
   
      Country current;
   
      // setting the x-axis range
      xAxis.setAutoRanging(false);
      xAxis.setLowerBound(countryLinkedList.getIndex(0).getStartYear());
      xAxis.setUpperBound(countryLinkedList.getIndex(0).getEndYear());
      xAxis.setTickUnit(1);
   
      // For School Enrollment
      if (indicator == IndicatorType.SCHOOL_ENROLLMENT)
      {
         while (itr.hasNext())
         {
            current = itr.next();
   
            XYChart.Series<Number, Number> resultSeriesPrimary = this.seriesFromCountry(current)[0];
            XYChart.Series<Number, Number> resultSeriesSecondary = this.seriesFromCountry(current)[1];
            this.getData().addAll(resultSeriesPrimary, resultSeriesSecondary);
   
         }
      }
      // For GDP per Capita
      while (itr.hasNext())
      {
         current = itr.next();
      
         XYChart.Series<Number, Number> resultSeries = this.seriesFromCountry(current)[0];
         this.getData().add(resultSeries);
      }
   }
}
