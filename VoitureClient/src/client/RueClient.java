package client;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;


public class RueClient  extends JComponent
{
	private static final long serialVersionUID = 5795668207956675396L;

	//public static final int SIZE_RUE=70;

	public static final int RUE		=	0;
	public static final int BOUE	=	1;
	public static final int FUEL	=	2;
	public static final int BOOST	=	3;

	private int type;

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int sizeRue;

	public RueClient(int type, int x1, int x2, int y1, int y2, int sizeRue)
	{
		super();
		this.type = type;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.sizeRue = sizeRue;
	}

	private void paintRoad(Graphics g)
	{
		paintRoad(0, 0, g);
	}

	private void paintRoad(int x, int y, Graphics g)
	{
		super.paintComponent(g);
		if(type == RUE)
		{
			g.setColor(Color.black);
		}
		else if(type == BOUE)
		{
			g.setColor(new Color(255, 127, 127));
		}
		else if(type == BOOST)
		{
			g.setColor(Color.gray);
		}
		else 
		{
			g.setColor(Color.green);
		}
		int x1 = this.x1-x;
		int y1 = this.y1-y;
		int x2 = this.x2-x;
		int y2 = this.y2-y;
		if(x1==x2)
		{
			if(y1 < y2)
			{
				g.fillRect(x1-sizeRue, y1, sizeRue*2, y2-y1);
			}
			else
			{
				g.fillRect(x1-sizeRue, y2, sizeRue*2, y1-y2);
			}
		}
		else
		{
			if(x1 < x2)
			{
				g.fillRect(x1, y1-sizeRue, x2-x1, sizeRue*2);
			}
			else
			{
				g.fillRect(x2, y1-sizeRue, x1-x2, sizeRue*2);
			}
		}
		g.fillOval(x1-sizeRue, y1-sizeRue, sizeRue*2, sizeRue*2);
		g.fillOval(x2-sizeRue, y2-sizeRue, sizeRue*2, sizeRue*2);
	}

	public void paintCenter(int x, int y, Graphics g)
	{
		super.paintComponent(g);
		int x1 = this.x1-x;
		int x2 = this.x2-x;
		int y1 = this.y1-y;
		int y2 = this.y2-y;
		g.setColor(Color.WHITE);
		if(x1==x2)
		{
			if(y1 < y2)
			{
				g.fillRect(x1-2, y1, 4, y2-y1);
			}
			else
			{
				g.fillRect(x1-2, y2, 4, y1-y2);
			}
		}
		else
		{
			if(x1 < x2)
			{
				g.fillRect(x1, y1-2, x2-x1, 4);
			}
			else
			{
				g.fillRect(x2, y1-2, x1-x2, 4);
			}
		}
	}

	protected void paintComponent(Graphics g)
	{
		paintRoad(g);
	}

	protected void paintComponent(int x, int y,Graphics g)
	{
		paintRoad(x,y,g);
	}
}
