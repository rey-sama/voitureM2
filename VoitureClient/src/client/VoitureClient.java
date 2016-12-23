package client;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class VoitureClient extends JComponent
{
	private BufferedImage image;

	private long id;
	private int x;
	private int y;
	private int orientation;
	
	private String name;

	public VoitureClient(int id, BufferedImage image, int x, int y, int orientation)
	{
		super();
		
		this.id = id;
		this.image = image;
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public VoitureClient(BufferedImage image)
	{
		this(0, image, 0, 0, 0);
	}
	
	public VoitureClient(BufferedImage image, String infos)
	{
		this(image);
		this.setPos(infos);
	}


	public long setPos(String text)
	{
		String var = null;
		String rest = text;
		var = rest.substring(0, rest.indexOf(";"));
		rest = rest.substring(rest.indexOf(";")+1);
		id = Long.parseLong(var);
		var = rest.substring(0, rest.indexOf(";"));
		rest = rest.substring(rest.indexOf(";")+1);
		name = var;
		
		var = rest.substring(0, rest.indexOf(";"));
		rest = rest.substring(rest.indexOf(";")+1);
		x = Integer.parseInt(var);

		var = rest.substring(0, rest.indexOf(";"));
		rest = rest.substring(rest.indexOf(";")+1);
		y = Integer.parseInt(var);

		orientation = Integer.parseInt(rest);

		return id;
	}
	
	protected void paintComponent(Graphics g)
	{
		
	}
	
	protected void paintComponent(int x, int y, Graphics g)
	{
		super.paintComponent(g);
		// create the transform, note that the transformations happen
		// in reversed order (so check them backwards)
		AffineTransform at = new AffineTransform();

		// 3. do the actual rotation
		at.translate(this.x-x,this.y-y);

		// 2. just a scale because this image is big

		// 1. translate the object so that you rotate it around the 
		//    center (easier :))

		at.scale(0.25, 0.25);
		at.rotate(Math.toRadians(orientation));
		at.translate(-image.getWidth()/2, -image.getHeight()/2);

		// draw the image
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, at, null);

		// continue drawing other stuff (non-transformed)
		//...

	}

	public void setOrientation(int or)
	{
		orientation = or;
	}

	public long getId()
	{
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
