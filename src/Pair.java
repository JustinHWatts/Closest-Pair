/**
 * Pair.java - Contains the information of a pair of points,
 * including each individual point along with the distance
 * between said points. Use command "javac Pair.java" to
 * compile. Run the full program through the 
 * "ClosestPairDriver" file.
 *
 * @author Justin Watts
 * @version 03/30/2016
 * @category ITEC 360
 */
public class Pair
{
	private static Point firstPoint;
	private static Point secondPoint;
	private static double distance;
	
	public Pair()
	{
		firstPoint = null;
		secondPoint = null;
		distance = 0.0;
	}
	
	public static Point getFirstPoint()
	{
		return firstPoint;
	}
	
	public static Point getSecondPoint()
	{
		return secondPoint;
	}
	
	public static double getDistance()
	{
		return distance;
	}
	
	public static void setFirstPoint(Point point)
	{
		firstPoint = point;
	}
	
	public static void setSecondPoint(Point point)
	{
		secondPoint = point;
	}
	
	public static void setDistance(Double distance_)
	{
		distance = distance_;
	}
	
	public String toString()
	{
		return firstPoint + " and " + secondPoint + "\nDistance: " + distance;
	}
}