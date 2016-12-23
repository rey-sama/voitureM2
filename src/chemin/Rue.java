package chemin;

public class Rue
{
	public static final int VERTICAL	=	0;
	public static final int HORIZONTAL	=	1;
	
	private Point p1;
	private Point p2;
	private int taille;

	private float ratioVitesse; //Indique de combien les voitures devrons accélérer ou ralentir

	public final int orientation;
	
	public Rue(Point p1, Point p2, int taille, float ratioVitesse) throws BadOrientationExeption
	{
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.taille = taille;
		this.ratioVitesse = ratioVitesse;
		
		if(p1.getX()==p2.getX())
		{
			orientation = VERTICAL;
		}
		else if(p1.getY()==p2.getY())
		{
			orientation = HORIZONTAL;
		}
		else
		{
			throw new BadOrientationExeption(this);
		}
	}

	public Rue(int x1, int y1, int x2, int y2, int taille, float ratioVitesse) throws BadOrientationExeption
	{
		this(new Point(x1, y1), new Point(x2, y2), taille, ratioVitesse);
	}

	private int distanceCentre(Point p)
	{
		if(orientation == HORIZONTAL)
		{
			if( (p1.getX()-p.getX()) * (p2.getX()-p.getX()) < 0)//Permet de voir si p.x est entre p1.x et p2.x
			{
				return Math.abs(p1.getY()-p.getY());
			}
			else
			{
				return Math.min(p1.distance(p), p2.distance(p));
			}
		}
		else
		{
			if( (p1.getY()-p.getY()) * (p2.getY()-p.getY()) < 0)//Permet de voir si p.y est entre p1.y et p2.y
			{
				return Math.abs(p1.getX()-p.getX());
			}
			else
			{
				return Math.min(p1.distance(p), p2.distance(p));
			}
		}
	}

	private int distanceCentre(Rue r)
	{
		if(r.orientation == this.orientation) //Si ils ont la même oritation
		{
			return Math.min(this.distanceCentre(r.p1), this.distanceCentre(r.p2));
//			if(this.orientation == HORIZONTAL) //Et qu'il sont horizontaux
//			{
//				return Math.abs(this.p1.getY()-r.p1.getY()); //On regarde la différence de leurs Y
//			}
//			else								//Sinon ils sont verticaux
//			{
//				return Math.abs(this.p1.getX()-r.p1.getX());  //On regarde la différence de leurs X
//			}
		}
		else //Si ils ont une orientation diférente
		{
			if(this.orientation==VERTICAL) //Le "this" dans cette méthode sera à l'HORIZONTAL -> évite de gérer des cas miroir
			{
				return r.distanceCentre(this);
			}
			else
			{
				if( (this.p1.getX()-r.p1.getX()) * (this.p2.getX()-r.p1.getX()) < 0 )
				{
					if( (this.p1.getY()-r.p1.getY()) * (this.p1.getY()-r.p2.getX()) < 0 )
					{
						return 0;
					}
					else
					{
						return Math.min(this.distanceCentre(r.p1), this.distanceCentre(r.p2));
					}
				}
				else
				{
					return Math.min(r.distanceCentre(this.p1), r.distanceCentre(this.p2));
				}
			}
		}
	}
	
	public int distance(Rue r)
	{
		return distanceCentre(r)-this.taille-r.taille;
	}
	
	public int distance(Point p)
	{
		return distanceCentre(p)-taille;
	}

	
	
	
	
	public Point getP1() {
		return p1;
	}
	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}
	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}

	public float getRatioVitesse() {
		return ratioVitesse;
	}
	public void setRatioVitesse(float ratioVitesse) {
		this.ratioVitesse = ratioVitesse;
	}
}
