package chemin;

public class Point
{
	private int x;
	private int y;
	
	public Point(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}
	
	public int distance(Point p)
	{
		int X = (int) Math.pow(p.x-this.x, 2);
		int Y = (int) Math.pow(p.y-this.y, 2);
		return (int) Math.sqrt(X+Y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
