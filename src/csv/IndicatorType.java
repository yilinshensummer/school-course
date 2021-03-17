package csv;

/**
 * An enum used to help indicate the kind of data that will be in a .csv file.
 *
 * @author Foothill College, Reece Kim
 */
public enum IndicatorType
{

   GDP_PER_CAPITA("GDP per capita (current US$)"),
   SCHOOL_ENROLLMENT_PRIMARY("School Enrollment In Primary (% net)"),
   SCHOOL_ENROLLMENT_SECONDARY("School Enrollment In Secondary (% net)"),
   SCHOOL_ENROLLMENT("School Enrollment In Primary and Secondary") ,
   INVALID("Invalid Data");

   private String label;

   // TODO : check this if it needs to be public
   IndicatorType(String label)
   {
      this.label = label;
   }

   public String getLabel()
   {
      return label;
   }
}
