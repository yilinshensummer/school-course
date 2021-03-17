package csv;

/**
 * A user defined exception class which extends java.io.IOException. Returns a message for when a file is poorly
 * formatted.
 *
 * @author Foothill College, Reece Kim
 */
public class InvalidFileFormatException extends java.io.IOException
{
   private String message;
   private String fileName;
   
   /**
    * Instantiates an InvalidFileFormatException object so it can inform the user of the poor file formatting.
    *
    * @param fileName        The name of the file where the exception occurred.
    * @param message         The message describing why the exception occurred.
    */
   public InvalidFileFormatException(String fileName, String message)
   {
      
      super("in " + fileName + ". " + message);
      this.fileName = fileName;
      this.message = "in " + fileName + ". " + message;
      
   }
   
   /**
    * Returns the message that was written in the constructor.
    *
    * @return        Returns a message which informs the user of the exception.
    */
   public String getMessage()
   {
      return message;
   }
   
}
