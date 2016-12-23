import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class TestClientGraphique extends JFrame
{
	private Image img;
	

	public TestClientGraphique()
	{
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		img = new ImageIcon("/home/rey-sama/ressource/voiture.jpg").getImage();
		this.add(new JComponent()
		{
			protected void paintComponent(Graphics g) {
				BufferedImage image=null;
				image = toBufferedImage(img);
				super.paintComponent(g);
				// create the transform, note that the transformations happen
				// in reversed order (so check them backwards)
				AffineTransform at = new AffineTransform();

				// 3. do the actual rotation
				at.translate(500,200);

				// 2. just a scale because this image is big
				at.scale(0.25, 0.25);

				// 1. translate the object so that you rotate it around the 
				//    center (easier :))
				at.translate(-image.getWidth()/2, -image.getHeight()/2);

				at.rotate(Math.PI/8);
				
				// draw the image
				Graphics2D g2d = (Graphics2D) g;
				g2d.drawImage(image, at, null);

				// continue drawing other stuff (non-transformed)
				//...

			}
		});
	}

	//	public void paint(Graphics g)
	//	{
	//		super.paint(g);
	//		BufferedImage bi = toBufferedImage(img);
	//		Graphics2D bi2D = bi.createGraphics();
	//		bi2D.rotate(Math.toRadians(90), 10, 10);
	//		
	//		g.drawImage(bi, 0, 0, null);
	//	}
	//	


	public static BufferedImage toBufferedImage(Image img)
	{
		if (img instanceof BufferedImage)
		{
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}
}
