package game;

import java.util.ArrayList;

public class Partie
{
	public long id;
	//public Map map; // Pas encore implémenté
	public ArrayList<Voiture> listVoiture;
	public ArrayList<ObjetBonusMalus> listObjet;
	
	public Partie()
	{
		listVoiture = new ArrayList<Voiture>();
		listObjet = new ArrayList<ObjetBonusMalus>();
	}
	
	public void addVoiture(Voiture v)
	{
		listVoiture.add(v);
	}
	
	public ArrayList<Voiture> getListVoiture()
	{
		return listVoiture;
	}
	public void setListVoiture(ArrayList<Voiture> listVoiture)
	{
		this.listVoiture = listVoiture;
	}
}
