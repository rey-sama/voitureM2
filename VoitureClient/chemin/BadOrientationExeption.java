package chemin;

public class BadOrientationExeption extends Exception
{
	private static final long serialVersionUID = 678419269705479495L;

	private Rue r;
	
	public BadOrientationExeption(Rue r)
	{
		super();
		this.r = r;
	}
	
	public String getMessage()
	{
		return r.getP1().getX() + " != " + r.getP2().getX() + " && " + r.getP1().getY() + " != " + r.getP2().getY();
	}
}
