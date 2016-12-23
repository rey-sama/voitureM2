package client.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.FenetreClient;

public class ClientKeyListener implements KeyListener
{

	public FenetreClient fc;
	
	public ClientKeyListener(FenetreClient fc)
	{
		super();
		this.fc = fc;
	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==37)
		{
			fc.setTournerGauche(true);
		}
		
		if(e.getKeyCode()==38)
		{
			fc.setAvancer(true);
		}
		
		if(e.getKeyCode()==39)
		{
			fc.setTournerDroite(true);
		}
		
		if(e.getKeyCode()==40)
		{
			fc.setReculer(true);
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode()==37)
		{
			fc.setTournerGauche(false);
		}
		
		if(e.getKeyCode()==38)
		{
			fc.setAvancer(false);
		}
		
		if(e.getKeyCode()==39)
		{
			fc.setTournerDroite(false);
		}
		
		if(e.getKeyCode()==40)
		{
			fc.setReculer(false);
		}
		
		if(e.getKeyCode()==32)
		{
			fc.setUseObj(true);
		}
		else
		{
			fc.setUseObj(false);
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

}
