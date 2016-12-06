/**
 * Point.java - Contains the information of a single point,
 * including both the x and y values of said point. Use the
 * command "javac Point.java" to compile. Run the full program 
 * through the "ClosestPairDriver" file.
 *
 * @author Justin Watts
 * @version 03/30/2016
 * @category ITEC 360
 */
public class Point
{
	private int x;
	private int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void setX(int x_)
	{
		if (x_ > 0)
			this.x = x_;
	}
	
	public void setY(int y_)
	{
		if (y_ > 0)
			this.y = y_;
	}
	
	public String toString()
	{
		return "(" + this.x + ", " + this.y + ")";
	}
}