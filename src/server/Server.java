package server;

import game.Partie;

import java.util.Hashtable;

public class Server //Servira de BDD temporaire
{
	private static Hashtable<Long, Utilisateur> hash = new Hashtable<Long, Utilisateur>();
	
	//private ArrayList<Partie> listPartie = new ArrayList<Partie>(); //Pour la dernièreversion
	private static Partie partie = new Partie();
	 
	
	public static Utilisateur addUtilisateur(String pseudo)
	{
		Utilisateur u = new Utilisateur(pseudo);
		partie.addVoiture(u.getVoiture());
		hash.put(u.getId(), u);
		return u;
	}
	
	public static Utilisateur getUtilisateur(Long id)
	{
		return hash.get(id);
	}

	public static Partie getPartie()
	{
		return partie;
	}
}
