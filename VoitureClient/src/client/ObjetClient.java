package client;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class ObjetClient extends JComponent
{
	private int x;
	private int y;
	
	private BufferedImage image;
	
	
	public ObjetClient(int x, int y, BufferedImage image)
	{
		super();
		this.x = x;
		this.y = y;
		this.image = image;
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
		at.translate(-image.getWidth()/2, -image.getHeight()/2);

		// draw the image
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, at, null);

		// continue drawing other stuff (non-transformed)
		//...

	}
}
