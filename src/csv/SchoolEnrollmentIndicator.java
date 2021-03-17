package csv;

/**
 * A subclass of Indicator that has attributes for primary and secondary school data and the year.  Also Overrides and
 * defines Indicator's undefined methods.
 *
 * @author Foothill College, Reece Kim
 */
public class SchoolEnrollmentIndicator extends Indicator
{
   
   private double netPrimary;
   private double netSecondary;
   
   /**
    * A constructor that sets the primary data to Invalid and takes and sets the year as well.
    *
    * @param year        The year the data was collected.
    */
   public SchoolEnrollmentIndicator(int year)
   {
      super(year);
      netPrimary = INVALID_DATA;
      netSecondary = INVALID_DATA;
   }
   
   /**
    * Sets the year and the data that was supposedly collected during that year.
    *
    * @param year                          The year the data was collected
    * @param primaryIndicatorData          The primary enrollment data of a specific year.
    * @param secondaryIndicatorData        The secondary enrollment data of a specific year
    */
   public SchoolEnrollmentIndicator(int year, double primaryIndicatorData, double secondaryIndicatorData)
   {
      super(year);
      netPrimary = primaryIndicatorData;
      netSecondary = secondaryIndicatorData;
   }
   
   /**
    * A setter method that sets netPrimary based off an array of type double.
    *
    * @param data        the primary data.
    */
   @Override
   public void setData(double[] data)
   {
      netPrimary = data[0];
      netSecondary = data[1];
   }
   
   /**
    * A getter method that returns netPrimary data in an array of type double.
    *
    * @return        returns the netPrimary data in an array of type double.
    */
   @Override
   public double[] getData()
   {
      return new double[]{netPrimary, netSecondary};
   }
   
   /**
    * Another setter method that sets netPrimary but using a primitive type double.
    *
    * @param netPrimary        The primary school data.
    */
   public void setPrimaryEnrollment(double netPrimary)
   {
      this.netPrimary = netPrimary;
   }
   
   /**
    * A getter method that returns netPrimary as a primitive double type.
    *
    * @return        returns the netPrimary data as a primitive double type.
    */
   public double getPrimaryEnrollment()
   {
      return netPrimary;
   }
   
   /**
    * A setter method that sets netSecondary using a primitive type double.
    *
    * @param netSecondary        Secondary school data.
    */
   public void setSecondaryEnrollment(double netSecondary)
   {
      this.netSecondary = netSecondary;
   }
   
   /**
    * A getter method that returns netSecondary as a primitive double type
    *
    * @return        returns the netSecondary data as a primitive double type.
    */
   public double getSecondaryEnrollment()
   {
      return netSecondary;
   }
   
   /**
    * A toString() method that returns a string representation of the data.
    *
    * @return        A string representation of the data.
    */
   @Override
   public String toString() {
      
      return String.format("(%.2f, %.2f)", netPrimary,netSecondary).replace("-1.00","");
   }
}
