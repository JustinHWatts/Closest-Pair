import java.io.*;
import java.util.*;

/**
 * ClosestPairView.java - Runs the command line loop for the
 * Closest Pair program, allowing for user input on what algorithm(s)
 * to use and how to read in the data for said algorithm(s). Also
 * handles the reading in of file data as well as the generation of
 * random points for use in the various algorithms. To compile this
 * file, use the "javac ClosestPairView.java" command. Run the full 
 * program through the "ClosestPairDriver" file.
 *
 * @author Justin Watts
 * @version 03/30/2016
 * @category ITEC 360
 */
public class ClosestPairView
{
	private List<Point> readInFile(File file)
	{
		BufferedReader reader = null;
		String currentInput = "";
		int count = 0;
		
		Point newPoint = null;
		int x = 0;
		int y = 0;
		
		List<Point> pointList = new ArrayList<Point>();
		
		try
		{
			reader = new BufferedReader(new FileReader(file));
			
		    while ((currentInput = reader.readLine()) != null)
		    {
		    	if (count == 0)
		    		Integer.parseInt(currentInput);
		    	else
		    	{
		    		newPoint = null;
		    		String[] xyArray = currentInput.split("[,\\s]+");
		    		x = Integer.parseInt(xyArray[0]);
		    		y = Integer.parseInt(xyArray[1]);
		    		
		    		newPoint = new Point(x, y);
		    		pointList.add(newPoint);
		    	}
		    	
		    	count++;
		    }
		}
		catch (FileNotFoundException exception)
		{
			exception.printStackTrace();
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			try
			{
				if (reader != null)
				{
					reader.close();
				}
			}
			catch (IOException exception)
			{
			}
		}
		
		return pointList;
	}
	
	private List<Point> generateList(int numPoints, int maxX, int maxY)
	{
		List<Point> pointList = new ArrayList<Point>();
		Random random = new Random();
		
		if (numPoints > -1)
		{
			for (int i = 0; i < numPoints; i++)
			{
				Point newPoint = new Point(random.nextInt(maxX + 1), random.nextInt(maxY + 1));
				pointList.add(newPoint);
			}
		}
		
		return pointList;
	}
	
	public void start()
	{
		Scanner scan = new Scanner(System.in);
		String currentInput = "";
		String filename = "";
		int numPoints = 0;
		int maxX = 0;
		int maxY = 0;
		
		ClosestPairModel model = new ClosestPairModel();
		List<Point> pointList = new ArrayList<Point>();
		Pair closestPair1 = new Pair();
		Pair closestPair2 = new Pair();
		
		while (!currentInput.equals("exit"))
		{
			System.out.println("Commands: 'Brute', 'Divide', 'Both', and 'Exit'");
			System.out.println("Please enter a command: ");
			currentInput = scan.next();
			currentInput = currentInput.toLowerCase();
			System.out.println("\n");
			
			switch (currentInput)
			{
			case "brute":
				System.out.println("Commands: 'File' and 'Generate'");
				System.out.println("Please enter a command: ");
				currentInput = scan.next();
				currentInput = currentInput.toLowerCase();
				System.out.println("\n");
				
				if (currentInput.equals("file"))
				{
					System.out.println("Please enter a filename: ");
					filename = scan.next();
					System.out.println("\n");
					File file = new File(filename);
					
					pointList = this.readInFile(file);
					long startTime = System.currentTimeMillis();
					closestPair1 = model.brute(pointList);
					long elapsedTime = System.currentTimeMillis() - startTime;
					
					System.out.println("Brute Force Algorithm from File");
					System.out.println(filename);
					System.out.println(pointList.size() + " points");
					System.out.println(closestPair1.toString());
					System.out.println(elapsedTime + " ms\n");
				}
				
				else if (currentInput.equals("generate"))
				{
					System.out.println("Please enter a number of points to generate: ");
					numPoints = scan.nextInt();
					
					System.out.println("Please enter a maximum value for x: ");
					maxX = scan.nextInt();
					System.out.println("Please enter a maximum value for y: ");
					maxY = scan.nextInt();
					System.out.println("\n");
					
					pointList = this.generateList(numPoints, maxX, maxY);
					long startTime = System.currentTimeMillis();
					closestPair1 = model.brute(pointList);
					long elapsedTime = System.currentTimeMillis() - startTime;
					
					System.out.println("Brute Force Algorithm from Generated Numbers");
					System.out.println("Max X Value: " + maxX);
					System.out.println("Max Y Value: " + maxY);
					System.out.println(pointList.size() + " points");
					System.out.println(closestPair1.toString());
					System.out.println(elapsedTime + " ms\n");
				}
				
				break;
				
			case "divide":
				System.out.println("Commands: 'File' and 'Generate'");
				System.out.println("Please enter a command: ");
				currentInput = scan.next();
				currentInput = currentInput.toLowerCase();
				System.out.println("\n");
				
				if (currentInput.equals("file"))
				{
					System.out.println("Please enter a filename: ");
					filename = scan.next();
					System.out.println("\n");
					File file = new File(filename);
					
					pointList = this.readInFile(file);
					long startTime = System.currentTimeMillis();
					closestPair1 = model.divide(pointList);
					long elapsedTime = System.currentTimeMillis() - startTime;
					
					System.out.println("Divide and Conquer Algorithm from File");
					System.out.println(filename);
					System.out.println(pointList.size() + " points");
					System.out.println(closestPair1.toString());
					System.out.println(elapsedTime + " ms\n");
				}
				
				else if (currentInput.equals("generate"))
				{
					System.out.println("Please enter a number of points to generate: ");
					numPoints = scan.nextInt();
					
					System.out.println("Please enter a maximum value for x: ");
					maxX = scan.nextInt();
					System.out.println("Please enter a maximum value for y: ");
					maxY = scan.nextInt();
					System.out.println("\n");
					
					pointList = this.generateList(numPoints, maxX, maxY);
					long startTime = System.currentTimeMillis();
					closestPair1 = model.divide(pointList);
					long elapsedTime = System.currentTimeMillis() - startTime;
					
					System.out.println("Divide and Conquer Algorithm from Generated Numbers");
					System.out.println("Max X Value: " + maxX);
					System.out.println("Max Y Value: " + maxY);
					System.out.println(pointList.size() + " points");
					System.out.println(closestPair1.toString());
					System.out.println(elapsedTime + " ms\n");
				}
				
				break;
				
			case "both":
				System.out.println("Commands: 'File' and 'Generate'");
				System.out.println("Please enter a command: ");
				currentInput = scan.next();
				currentInput = currentInput.toLowerCase();
				System.out.println("\n");
				
				if (currentInput.equals("file"))
				{
					System.out.println("Please enter a filename: ");
					filename = scan.next();
					System.out.println("\n");
					File file = new File(filename);
					
					pointList = this.readInFile(file);
					long startTime1 = System.currentTimeMillis();
					closestPair1 = model.brute(pointList);
					long elapsedTime1 = System.currentTimeMillis() - startTime1;
					long startTime2 = System.currentTimeMillis();
					closestPair2 = model.divide(pointList);
					long elapsedTime2 = System.currentTimeMillis() - startTime2;
					
					System.out.println("Both Algorithms from File");
					System.out.println(filename);
					System.out.println(pointList.size() + " points\n");
					
					System.out.println("Brute Force:");
					System.out.println(closestPair1.toString());
					System.out.println(elapsedTime1 + " ms\n");
					
					System.out.println("Divide and Conquer:");
					System.out.println(closestPair2.toString());
					System.out.println(elapsedTime2 + " ms\n");
				}
				
				else if (currentInput.equals("generate"))
				{
					System.out.println("Please enter a number of points to generate: ");
					numPoints = scan.nextInt();
					
					System.out.println("Please enter a maximum value for x: ");
					maxX = scan.nextInt();
					System.out.println("Please enter a maximum value for y: ");
					maxY = scan.nextInt();
					System.out.println("\n");
					
					pointList = this.generateList(numPoints, maxX, maxY);
					long startTime1 = System.currentTimeMillis();
					closestPair1 = model.brute(pointList);
					long elapsedTime1 = System.currentTimeMillis() - startTime1;
					long startTime2 = System.currentTimeMillis();
					closestPair2 = model.divide(pointList);
					long elapsedTime2 = System.currentTimeMillis() - startTime2;
					
					System.out.println("Both Algorithms from Generated Numbers");
					System.out.println("Max X Value: " + maxX);
					System.out.println("Max Y Value: " + maxY);
					System.out.println(pointList.size() + " points\n");
					
					System.out.println("Brute Force:");
					System.out.println(closestPair1.toString());
					System.out.println(elapsedTime1 + " ms\n");
					
					System.out.println("Divide and Conquer:");
					System.out.println(closestPair2.toString());
					System.out.println(elapsedTime2 + " ms\n");
				}
				
				break;
				
			case "exit":
				break;
				
			default:
				System.out.println("Invalid input\n");
			}
		}
		
		scan.close();
	}
}