package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.Server;
import server.Utilisateur;

public class ConnexionServlet extends HttpServlet
{
	private static final long serialVersionUID = -7490197099527975893L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		Utilisateur u = Server.addUtilisateur(req.getParameter("pseudo"));
		System.out.println(u.getInfo());
		res.getOutputStream().print(u.getInfo()); //Protocole de r�ponse : 
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doGet(req, res);
	}
}
