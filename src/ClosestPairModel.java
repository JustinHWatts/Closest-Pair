import java.util.*;

/**
 * ClosestPairModel.java - Handles the math behind the various
 * algorithms for determining the closest pair in a set of points.
 * Allows the program to use either brute force or divide and conquer
 * algorithms. Also handles the math of determining the distance between 
 * two points. To compile this file, use the "javac ClosestPairModel.java"
 * command. Run the full program through the "ClosestPairDriver" file.
 *
 * @author Justin Watts
 * @version 03/30/2016
 * @category ITEC 360
 */
public class ClosestPairModel
{
	private double determineDistance(Point firstPoint, Point secondPoint)
	{
		double xDistance = 0.0;
		double yDistance = 0.0;
		double totalDistance = 0.0;
		
		xDistance = secondPoint.getX() - firstPoint.getX();
		yDistance = secondPoint.getY() - firstPoint.getY();
		totalDistance = Math.hypot(xDistance, yDistance);
		
		return totalDistance;
	}
	
	private void sortX(List<Point> list)
	{
	    Collections.sort(list, new Comparator<Point>()
	    {
	        public int compare(Point firstPoint, Point secondPoint)
	        {
	        	if (firstPoint.getX() < secondPoint.getX())
	        		return -1;
	        	if (firstPoint.getX() > secondPoint.getX())
	        		return 1;
	        	else
	        		return 0;
	        }
	    });
	}

	private void sortY(List<Point> list)
	{
	    Collections.sort(list, new Comparator<Point>()
	    {
	        public int compare(Point firstPoint, Point secondPoint)
	        {
	        	if (firstPoint.getY() < secondPoint.getY())
	        		return -1;
	        	if (firstPoint.getY() > secondPoint.getY())
	        		return 1;
	        	else
	        		return 0;
	        }
	    });
	}
	
	private Pair divide(List<Point> listSortedX, List<Point> listSortedY)
	{
		Pair closestPair = new Pair();
		Pair rightClosestPair = new Pair();
		
		if (listSortedX.size() <= 3)
			return this.brute(listSortedX);
		
		int mid = listSortedX.size()/2;
		List<Point> left = listSortedX.subList(0, mid);
		List<Point> right = listSortedX.subList(mid, listSortedX.size());
		List<Point> currentList = new ArrayList<Point>(left);
		this.sortY(currentList);
	    closestPair = divide(left, currentList);
	    
	    currentList.clear();
	    currentList.addAll(right);
	    sortY(currentList);
	    rightClosestPair = divide(right, currentList);
	    
	    if (rightClosestPair.getDistance() < closestPair.getDistance())
	    	closestPair = rightClosestPair;
		
	    
	    double closestDistance = 0.0;
	    double check = 0.0;
	    int middleX = 0;
	    Point currentFirstPoint = null;
		Point currentSecondPoint = null;
		Double distance = 0.0;
	    
	    currentList.clear();
	    closestDistance = closestPair.getDistance();
	    middleX = right.get(0).getX();
	    
	    for (Point currentPoint : listSortedY)
	    {
	    	check = middleX - currentPoint.getX();
	    	if (check < 0)
	    		check = check * -1;
	    	
	    	if (check < closestDistance)
	    		currentList.add(currentPoint);
	    }
	    
	    
	    for (int i = 0; i < currentList.size() - 1; i++)
	    {
	    	currentFirstPoint = currentList.get(i);
	    	
	    	for (int j = i + 1; j < currentList.size(); j++)
	    	{
	    		currentSecondPoint = currentList.get(j);
				distance = this.determineDistance(currentFirstPoint, currentSecondPoint);
				
				if (distance < closestPair.getDistance())
				{
					closestPair.setFirstPoint(currentFirstPoint);
					closestPair.setSecondPoint(currentSecondPoint);
					closestPair.setDistance(distance);
					closestDistance = distance;
				}
	    	}
	    }
	    
		return closestPair;
	}
	
	public Pair brute(List<Point> list)
	{
		Pair closestPair = new Pair();
		int pointCount = list.size();
		Point currentFirstPoint = null;
		Point currentSecondPoint = null;
		Double distance = 0.0;
		
		if (pointCount >= 2)
		{
			Pair.setFirstPoint(list.get(0));
			Pair.setSecondPoint(list.get(1));
			distance = this.determineDistance(Pair.getFirstPoint(), Pair.getSecondPoint());
			Pair.setDistance(distance);
			
			for (int i = 0; i < pointCount - 1; i++)
			{
				currentFirstPoint = list.get(i);
				
				for (int j = i + 1; j < pointCount; j++)
				{
					currentSecondPoint = list.get(j);
					distance = this.determineDistance(currentFirstPoint, currentSecondPoint);
					
					if (distance < Pair.getDistance())
					{
						Pair.setFirstPoint(currentFirstPoint);
						Pair.setSecondPoint(currentSecondPoint);
						Pair.setDistance(distance);
					}
				}
			}
		}
		
		return closestPair;
	}
	
	public Pair divide(List<Point> list)
	{
		if (list.size() <= 3)
			return this.brute(list);
		
		Pair closestPair = new Pair();
		
		List<Point> listSortedX = new ArrayList<Point>(list);
		sortX(listSortedX);
		List<Point> listSortedY = new ArrayList<Point>(list);
		sortY(listSortedY);
		
		this.divide(listSortedX, listSortedY);
		
		return closestPair;
	}
}