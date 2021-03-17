package csv;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Holds the data for a country with its name of type string and data of indicator GDP or SchoolEnrollment. Has two
 * constructors, an equals method to compare the name of two countries, getter and setter methods for the attributes, or
 * to get indicator data of a specfic year, and has a method that allows the user to get the data for a period of years.
 *
 * @author Foothill College, Reece Kim, Yilin Shen
 */
public class Country
{
   private String name;
   private LinkedList<Indicator> indicators;
   private int endYear;
   private int startYear;
   
   public final static String REPLACE_MISSING_DATA = "";
   public final static int MISSING_DATA = -1;
   
   /**
    * Another constructor that sets the name of a country.
    *
    * @param name        The name of a country.
    */
   public Country(String name)
   {
      this.name = name;
      indicators = new LinkedList<Indicator>();
   }
   
   /**
    * Adds a new Indicator object to the indicators LinkedList attribute.
    *
    * @param data        Indicator data.
    */
   public void addIndicator(Indicator data)
   {
      
      if (indicators.size() == 0)
      {
         indicators.add(data);
         return;
      }
      
      if (indicators.contains(data) != null)
      {
         return;
      }
      
      indicators.add(data);
      
      startYear = indicators.getIndex(0).getYear();
      endYear = (startYear + indicators.size()) - 1;
   }
   
   /**
    * An equals method that checks if the name of two countries are the same.
    *
    * @param objectOfInterest        The object that will be checked if it equals this.
    * @return                        A boolean value that tells user if the country names equals each other.
    */
   public boolean equals(Object objectOfInterest)
   {
      return (objectOfInterest instanceof String && this.name.equals(objectOfInterest))
              || (objectOfInterest instanceof Country && this.name.equals(((Country) objectOfInterest).getName()));
   }
   
   /**
    * A getter method for the name of a country.
    *
    * @return        Returns the name attribute of a country.
    */
   public String getName()
   {
      return name;
   }
   
   /**
    * A getter method for the year the data starts on.
    *
    * @return        Returns the startingYear of the data.
    */
   public int getStartYear()
   {
      return startYear;
   }
   
   /**
    * A getter method for the year the data ends on.
    *
    * @return        Returns the end year of the data.
    */
   public int getEndYear()
   {
      return endYear;
   }
   
   /**
    * Returns the indicator data for a specified year.
    *
    * @param requestedYear                    The requested year that the data will be from.
    * @return                                 Returns the indicator data for a specified year.
    * @throws IllegalArgumentException        If the requestedYear is invalid, throws exception.
    */
   public Indicator getIndicatorForYear(int requestedYear) throws IllegalArgumentException
   {
      
      int requestedIndex = requestedYear - startYear;
      // Find a new way to get endYear
      int endYear = getEndYear();
      
      if (indicators.getIndex(0) == null)
      {
         return null;
      }
      if (requestedIndex < 0)
         throw new IllegalArgumentException("Invalid request out of range, year entered is smaller than startYear.");
      if (requestedYear > endYear)
         throw new IllegalArgumentException("Invalid request out of range, year entered is larger than endYear");
      else
      {
         return indicators.getIndex(requestedIndex);
      }
      
   }
   
   /**
    * Sets the data of a specific requestedYear in the indicators array.
    *
    * @param requestedYear                    The requested year where the data will be set.
    * @param data                             The data that will be set.
    * @throws IllegalArgumentException        If the requestedYear is invalid throws exception.
    */
   public void setIndicatorForYear(int requestedYear, Indicator data) throws IllegalArgumentException
   {
      
      // If the first index is null the firstYear is null, startYear equals requested year, and set data to index
      if (indicators.getIndex(0) == null)
      {
         startYear = requestedYear;
         indicators.add(data);
      }
      
      int requestedIndex = requestedYear - startYear;
      int endYear = startYear + indicators.size() - 1;
      
      if (requestedIndex < 0)
         throw new IllegalArgumentException("Invalid request out of range, year entered is smaller than startYear.");
      else if (requestedYear > endYear)
         throw new IllegalArgumentException("Invalid request out of range, year entered is larger than endYear");
      else if (requestedYear == startYear)
      {
         indicators.add(data);
      }
      else
         indicators.insertAtIndex(data, requestedIndex);
   }
   
   /**
    * Checks if the arguments for the period are valid, if so returns a 1D array of type Indicator with data, else
    * throws an exception.
    *
    * @param requestedStartYear               The year the period will start on.
    * @param requestedEndYear                 The year the period will end on.
    * @return                                 Returns an array of Indicator data.
    * @throws IllegalArgumentException        Throws exception if request is invalid.
    */
   public Indicator[] getIndicatorForPeriod(int requestedStartYear, int requestedEndYear)
   {
      startYear = indicators.getIndex(0).getYear();
      endYear = indicators.getIndex(0).getYear() + indicators.size() -1;
      int startTime = Math.max(requestedStartYear, startYear);
      int endTime = Math.min(requestedEndYear, endYear);
      
      if (requestedEndYear < startYear || endYear < requestedStartYear) {
         throw new IllegalArgumentException("both request start and end year are out of range");
      }
      if (requestedEndYear < requestedStartYear){
         throw new IllegalArgumentException("requested start year and end year are in inverted order");
      }
      if (requestedStartYear < startYear || requestedEndYear > endYear) {
         System.out.println(String.format("Invalid request of start and end year %s, %s. Valid period  is %s to %s",
                 requestedStartYear, requestedEndYear, startTime, endTime));
      }
      
      Indicator[] result = new Indicator[endTime - startTime + 1];
      Iterator<Indicator> itr = indicators.iterator();
      int arrayIndex = 0;
      while(itr.hasNext() && arrayIndex < result.length)
      {
         result[arrayIndex] = itr.next();
         arrayIndex++;
      }
      
      return result;
   }
   
   /**
    * Displays the name of the country and the indicator data.
    *
    * @return        Returns a string representation of the data.
    */
   public String toString()
   {
      
      StringBuilder result = new StringBuilder();
      
      result.append(String.format("%52s", this.name));
      
      for (Indicator data: indicators)
      {
         result.append(String.format("%20s", data.toString()));
      }
      result.append(String.format("\n"));
      
      return result.toString();
   }
}

