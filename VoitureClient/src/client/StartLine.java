package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class StartLine extends Component
{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public StartLine(String str)
	{
		System.out.println(str);
		x1 = Integer.parseInt(str.replaceAll(";.*$",""));
		str = str.replaceAll("^[^;]*;", "");
		
		y1 = Integer.parseInt(str.replaceAll(";.*$",""));
		str = str.replaceAll("^[^;]*;", "");
		
		x2 = Integer.parseInt(str.replaceAll(";.*$",""));
		str = str.replaceAll("^[^;]*;", "");
		
		y2 = Integer.parseInt(str.replaceAll(";.*$",""));
		str = str.replaceAll("^[^;]*;", "");
	}
	
	public void paintComponent(int center_x, int center_y, Graphics g)
	{
		g.setColor(Color.green);
		System.out.println(x1  + " " + x2 + " " + y1 + " " + y2);
		
		System.out.println((x2-x1) +" " + (y2-y1));
		
		g.fillRect(x1-center_x-1, y1-center_y-1, x2-x1+2, y2-y1+2);
	}
}
