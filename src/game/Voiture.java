package game;

public class Voiture {
	public static final int ANGLE = 1;
	public static final int DESCELERATION = 1;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long id;
	private int x;
	private int y;
	public int acceleration;
	public int frein;
	private int orientation;
	public int vitesseMin;
	public int vitesse;
	public int vitesseMax;
	public int jaugeEssenceMax;
	public int jaugeEssence;
	public int diminutionCarburant;
	public ObjetBonusMalus obj;
	public ImpactBonusMalus listBonusMalus;
	
	public Voiture(long id, int x, int y, int acceleration, int frein, int vitesseMin, int vitesseMax, int jaugeEssenceMax, int diminutionCarburant)
	{
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.acceleration = acceleration;
		this.frein = frein;
		this.vitesseMin = vitesseMin;
		this.vitesseMax = vitesseMax;
		this.jaugeEssenceMax = jaugeEssenceMax;
		this.diminutionCarburant = diminutionCarburant;
		
		this.vitesse = 0;
		this.jaugeEssence = jaugeEssenceMax;
	}
	
	/* liste des fonctions */
	public void tournerGauche()
	{
		orientation-=ANGLE;
	}
	public void tournerDroite()
	{
		orientation+=ANGLE;
	}
	public void avancer()
	{
		x = (int)(x + vitesse*Math.cos(orientation*Math.PI/180));
		y = (int)(y + vitesse*Math.sin(orientation*Math.PI/180));
		vitesse += acceleration;
		if(vitesse > vitesseMax)
		{
			vitesse = vitesseMax;
		}
	}
	public void reculer()
	{
		x = (int)(x + vitesse*Math.cos(orientation*Math.PI/180));
		y = (int)(y + vitesse*Math.sin(orientation*Math.PI/180));
		
		if(vitesse > 0)
		{
			vitesse -= frein;
		}
		else
		{
			vitesse -= acceleration;
			if(vitesse < vitesseMin)
			{
				vitesse = vitesseMin;
			}
		}
	}
	public void decelerer()
	{
		x = (int)(x + vitesse*Math.cos(orientation*Math.PI/180));
		y = (int)(y + vitesse*Math.sin(orientation*Math.PI/180));
		if(vitesse>0)
		{
			vitesse-=DESCELERATION;
		}
		else if(vitesse<0)
		{
			vitesse+=DESCELERATION;
		}
	}
	
	public long getId()
	{
		return id;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
	

	public int getOrientation() {
		return orientation;
	}

	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}

	public void setOrientation(int orientation)
	{
		this.orientation = orientation;
	}

	public String getInfo()
	{
		return id + ";" + x + ";" + y + ";" + orientation;
	}

}
