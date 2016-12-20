package servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.Partie;
import game.Voiture;
import server.Server;
import server.Utilisateur;

public class GamingServlet extends HttpServlet
{
	private static final long serialVersionUID = -7490197099527975893L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		//System.out.println("''"+req.getRequestURL()+"''");
		long id = Long.parseLong(req.getParameter("id"));
		Utilisateur u = Server.getUtilisateur(id);
		Voiture v = u.getVoiture();
		if("1".equals(req.getParameter("gauche")))
		{
			v.tournerGauche();
		}
		else if("1".equals(req.getParameter("droite")))
		{
			v.tournerDroite();
		}
		
		if("1".equals(req.getParameter("reculer")))
		{
			v.reculer();
		}
		else if("1".equals(req.getParameter("avancer")))
		{
			v.avancer();
		}
		else
		{
			v.decelerer();
		}
		//System.out.println("--------------------\n"+v.getId()+" "+returnAllInfo(v));
		res.getOutputStream().print(returnAllInfo(v));
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doGet(req, res);
	}
	
	public String returnAllInfo(Voiture v)
	{
		Partie p = Server.getPartie();
		String str = p.getListVoiture().size()+"\t";
		str+=v.getInfo()+"\t";
		for(Voiture vNext : p.getListVoiture())
		{
			if(!(v.equals(vNext)))
			{
				str+=vNext.getInfo()+"\t";
			}
		}
		return str;
	}
}
