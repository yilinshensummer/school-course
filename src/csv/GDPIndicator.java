package csv;

/**
 * A subclass of Indicator which contains an attribute for the GDP per capita of a country and a year.  Also Overrides
 * and defines Indicator's undefined methods.
 *
 * @author Foothill College, Yilin Shen
 */
public class GDPIndicator extends Indicator
{
   
   private double gdpPerCapita;
   
   /**
    * A constructor that sets the GDP per capita to Invalid and takes as well as sets year.
    *
    * @param year        The year the data was collected.
    */
   public GDPIndicator(int year)
   {
      super(year);
      gdpPerCapita = INVALID_DATA;
   }
   
   /**
    * A constructor which sets GDP per capita and the year.
    *
    * @param year                The year the data was collected.
    * @param gdpPerCapita        The GDP per capita of a country.
    */
   public GDPIndicator(int year, double gdpPerCapita)
   {
      super(year);
      this.gdpPerCapita = gdpPerCapita;
   }
   
   /**
    * A setter method which sets the GDP per Capita.
    *
    * @param data        A double array which the GDP per capita is found.
    */
   @Override
   public void setData(double[] data)
   {
      gdpPerCapita = data[0];
   }
   
   /**
    * A getter method which gets the GDP per Capita.
    *
    * @return        Returns the GDP per Capita as an array of type double.
    */
   @Override
   public double[] getData()
   {
      return new double[]{gdpPerCapita};
   }
   
   /**
    * A toString() method that returns a string representation of the data.
    *
    * @return        A string representation of the data.
    */
   @Override
   public String toString()
   {
      return String.format("(%.2f)", gdpPerCapita).replace("-1.00","");
   }
}

