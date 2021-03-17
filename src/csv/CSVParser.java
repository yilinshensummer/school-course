package csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static csv.IndicatorType.*;

public class CSVParser {
   String indicator;
   private final String filename;
   String [] countryName;
   int[] yearLabels;
   double[][]dataTable;
   int yearCount;
   
   /**
    * CSVParser get filename, throw exception and read file.
    * @param filename                          file name of the resource
    * @throws InvalidFileFormatException       file format in valid
    * @throws FileNotFoundException            file not found
    */
   public CSVParser(String filename) throws InvalidFileFormatException, FileNotFoundException {
      this.filename = filename;
      
      readCSVFile();
      
   }
   
   /**
    * read csv file and parse components of each line, throw not found and invalid exceptions
    * @throws InvalidFileFormatException
    * @throws FileNotFoundException
    */
   private void readCSVFile() throws InvalidFileFormatException, FileNotFoundException {
      
      final int MISSING_DATA = -1;
      File infile = new File(filename);
      
      Scanner CSVParser = new Scanner(infile);
      System.out.println(filename);
      if (filename == null || !filename.contains(".csv")){
         throw new FileNotFoundException();
      }
      
      String line1 = CSVParser.nextLine();
      if(!line1.contains("Data Source")){
         throw new InvalidFileFormatException(filename, "Data Source at Line1");
      }
      
      String line2 = CSVParser.nextLine();
      if(!line2.contains("Indicator")){
         throw new InvalidFileFormatException(filename, "Indicator at Line2");
      }
      indicator = line2.split(",")[1];
      
      String line3 = CSVParser.nextLine();
      if(!line3.contains("Last Updated Date")){
         throw new InvalidFileFormatException(filename, "Last Updated Date at Line3");
      }
      String lastUpdateDate = line3.split(",")[1];
      
      String line4 = CSVParser.nextLine();
      if(!line4.contains("Number of Countries")){
         throw new InvalidFileFormatException(filename, "Number of Countries at Line4");
      }
      int numberOfCoutries =Integer.parseInt(line4.split(",")[1]);
      countryName = new String[numberOfCoutries];
      
      String line5 = CSVParser.nextLine();
      if(!line5.contains("Country Name")){
         throw new InvalidFileFormatException(filename, "Country Name at Line5");
      }
      
      String[]years = line5.split(",");
      
      yearCount = years.length - 1;
      yearLabels = new int[yearCount];
      for (int i = 1; i < years.length; i++){
         yearLabels[i-1] = Integer.parseInt(years[i]);
      }
      
      dataTable = new double[numberOfCoutries][yearCount];
      int rowCount = 0;
      while (CSVParser.hasNextLine()){
         String line = CSVParser.nextLine();
         String country;
         String[] tokens;
         if(line.contains("\"")){
            String [] tokens1 = line.split("\"");
            tokens= tokens1[2].split(",",-1);
            country = tokens1[1];
         }
         else {
            tokens = line.split(",",-1);
            country = tokens[0];
         }
         
         countryName [rowCount] = country;
         double[] countryData = new double[tokens.length - 1];
         if(countryData.length!= yearCount){
            throw new InvalidFileFormatException(filename,"Number of data doesn't match given number of years");
         }
         for (int i = 1; i < tokens.length; i++){
            String rate = tokens[i];
            if(rate.equals("")){
               countryData[i - 1] = MISSING_DATA;
            } else {
               countryData[i - 1] = Double.parseDouble(rate);
            }
         }
         dataTable[rowCount] = countryData;
         rowCount++;
      }
      if(rowCount != numberOfCoutries){
         throw new InvalidFileFormatException(filename,"Number of Countries doesn't match given number ");
      }
      //System.out.println(indicator + " updated at " + lastUpdateDate);
      CSVParser.close();
      
   }
   
   /**
    * define accessor methods which return country names
    * @return                     country Names
    */
   public String[] getCountryNames() {
      
      return countryName;
   }
   
   /**
    * accessor methods which return array of years read from the input file
    * @return                      array of yearsread from the input file
    */
   public int[] getYearLabels() {
      return yearLabels;
   }
   
   /**
    *define accessor methods which return data table of countries
    * @return              data table of country info in each year
    */
   public double[][] getParsedTable() {
      return dataTable;
   }
   
   /**
    * define accessor methods which return number of years
    * @return          return number of years
    */
   public int getNumberOfYears() {
      return yearCount;
   }
   
   /**
    * The method will check the indicator type from files.
    * @return           indicator Type
    */
   public int getStartYear(){ return yearLabels[0];}
   
   public IndicatorType getIndicatorType(){
      
      if(indicator.equals("GDP per capita (current US$)")){
         return GDP_PER_CAPITA;
         
      }
      if(indicator.equals("School Enrollment In Primary (% net)")){
         return SCHOOL_ENROLLMENT_PRIMARY;
         
      }
      if(indicator.equals("School Enrollment In Secondary (% net)")){
         return SCHOOL_ENROLLMENT_SECONDARY;
         
      }
      return INVALID;
   }
}
