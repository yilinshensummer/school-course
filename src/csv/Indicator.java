package csv;

/**
 * An abstract class that serves as a super class and template for the other more specific indicator classes.  Has
 * a constructor, getter for th year, two accessor methods for Data, anda toString.
 *
 * @author Foothill College, Yilin Shen
 */
public abstract class Indicator
{
   
   private int year;
   
   protected final int INVALID_DATA = -1;
   
   /**
    * A method that will specify the data was collected in the subclasses.
    *
    * @param year        takes the year of the data.
    */
   public Indicator(int year)
   {
      this.year = year;
   }
   
   /**
    * A getter method that returns the attribute year.
    *
    * @return        Returns the attribute year.
    */
   public int getYear()
   {
      return year;
   }
   
   /**
    * A setter method that sets the attribute year.
    *
    * @param year        the attribute year.
    */
   public void setYear(int year)
   {
      this.year = year;
   }
   
   /**
    * An abstract setter method that set the data of a subclass.
    *
    * @param data        takes a double array.
    */
   public abstract void setData(double[] data);
   
   /**
    * An abstract getter method that will return data in the subclass.
    *
    */
   public abstract double[] getData();
   
   /**
    * An abstract to toString() method that will return a string representation of the data in a subclass.
    *
    */
   public abstract String toString();
   
}
