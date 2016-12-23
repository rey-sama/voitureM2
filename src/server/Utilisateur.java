package server;

import game.Partie;
import game.Voiture;

public class Utilisateur
{
	private static int ID = 0; 
	
	private long id;
	private String pseudo;
	private Voiture voiture;
	private Partie partie;
	public Utilisateur(String pseudo)
	{
		super();
		this.id = ID;
		this.pseudo = pseudo;
		int acceleration = 1;
		int frein = 1;
		int vitesseMin = -90;
		int vitesseMax = 90;
		int jaugeEssenceMax = 5000;
		int diminutionCarburant = 2;
		this.voiture = new Voiture(id, 100, 100, acceleration, frein, vitesseMin, vitesseMax, jaugeEssenceMax, diminutionCarburant);
		
		Utilisateur.ID++;
	}


	public long getId()
	{
		return id;
	}
		
	public Voiture getVoiture()
	{
		return voiture;
	}
	
	public String getInfo()
	{
		return voiture.getInfo();
	}
}
