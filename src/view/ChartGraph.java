package view;

import csv.IndicatorType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.DataModel;
/*import model.IndicatorType;*/

import static csv.IndicatorType.GDP_PER_CAPITA;
import static csv.IndicatorType.SCHOOL_ENROLLMENT;
/*import static model.IndicatorType.GDP_PER_CAPITA;
import static model.IndicatorType.INVALID_DATA;
import static model.IndicatorType.SCHOOL_ENROLLMENT;*/

import java.io.FileNotFoundException;

import csv.InvalidFileFormatException;

/**
 * Instantiates an JavaFX application which creates a model of the data.
 * Uses the model to instantiate an object of type  javafx.scene.chart.LineChart
 * via the GraphView class. Then sets up the scene with basic modification to
 * the stage.
 *
 * @author Foothill College, Reece Kim, Yilin Shen
 */
public class ChartGraph extends Application
{	
	// private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.INVALID;
	private static final IndicatorType DEFAULT_INDICATOR = GDP_PER_CAPITA;
	// private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.SCHOOL_ENROLLMENT;


	/**
	 * Called by launch method of Application
	 * @param stage: Stage
	 */
	@Override
	public void start(Stage stage) 
	{		
		// NOTE: Make sure to use relative path instead of specifying the entire path
		//       such as (/Users/alicew/myworkspace/so_on_and_so_forth).

		// Example of invalid input file
		final String [] INVALID_INPUT = {"resources/childrenEnrolled_invalid.csv"};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT = { "resources/gdpPerCapita.csv"};

		// Example of input file for net school enrollment for:
		// [0] primary grade
		// [1] secondary grade
		final String [] ENROLLMENT_INPUT = { "resources/childrenEnrolledInPrimary.csv", 
				"resources/childrenEnrolledInSecondary.csv"
		};

		// Displays graph* of data by country.
		// TODO: Define the view such that it takes the model as input.
		//       Construct the x and y axis using a NumberAxis, label the axis.
		GraphView graphView = null;	    
		IndicatorType selectedIndicator = DEFAULT_INDICATOR;

		// Provides access to CSV data.
		// TODO: Define the data model by parsing the CSV input file(s).
		//       Provide accessor methods to your the parsed data.
		try 
		{
			switch(selectedIndicator)
			{
			case GDP_PER_CAPITA:
				DataModel gdpModel = new DataModel(GDP_INPUT);
				graphView = new GraphView(gdpModel, GDP_PER_CAPITA);
				break;
			case SCHOOL_ENROLLMENT:
				DataModel enrollmentModel = new DataModel(ENROLLMENT_INPUT);
				graphView = new GraphView(enrollmentModel, SCHOOL_ENROLLMENT);
				break;
			case INVALID:
				DataModel invalidModel = new DataModel(INVALID_INPUT);
				break;
			default:
				System.out.println("WARNING: Invalid indicator selected. Exiting program.");
			}
		}
		catch (FileNotFoundException e) 
		{
			System.out.printf("Invalid filename");
			System.exit(0);
		}
		catch (InvalidFileFormatException e)
		{
			System.out.printf("Invalid file format %s\n", e.getMessage());
			System.exit(0);
		}

		// TODO: Define the update() method for the model such that:
		//       - Gets all the data** from the model
		//       - Creates a random list of elements from the data.
		//       - Traverses each element and creates a series (i.e. line) 
		//       - Adds the series*** to it's Observablelist.

		//   * Here, displays graph of Indicator data by country.
		//
		//  ** Here, our data is composed of Country objects.
		// 
		// *** Get an instance of javafx.collections.ObservableList via a call 
		//     to getData() method.
		// https://docs.oracle.com/javafx/2/api/javafx/scene/chart/XYChart.html#getData()
		graphView.update();

		// Creates a scene and adds the graph to the scene.
		Scene scene = new Scene(graphView);

		// Places the scene in the stage
		stage.setScene(scene);

		// Set the stage title
		stage.setTitle("Your Own Title");

		// Display the stage
		stage.show();
	}

	/**
	 * Launches a standalone JavaFx App
	 */
	public static void main(String[] args) 
	{		
		launch();
	}
}