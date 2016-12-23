package client;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.listener.ClientKeyListener;

public class FenetreClient extends JFrame
{
	public static final BufferedImage myCar			=toBufferedImage("voiture");
	public static final BufferedImage foeCar		=toBufferedImage("voiture1");
	public static final BufferedImage banana		=toBufferedImage("banana");
	public static final BufferedImage champignon	=toBufferedImage("champi");
	public static final BufferedImage giver			=toBufferedImage("giver");
	
	public static long id; //TODO mettre en private non static

	private boolean avancer;
	private boolean reculer;
	private boolean tournerDroite;
	private boolean tournerGauche;
	private boolean useObj;
	
	private ArrayList<RueClient> rues;
	private ArrayList<RueClient> boue;
	private ArrayList<RueClient> boost;
	private ArrayList<RueClient> fuel;
	
	private int centerX;
	private int centerY;
	
	private LinkedList<VoitureClient> voitures;
	private LinkedList<ObjetClient> objects;
	
	private StartLine startLine;
	
	private BufferedImage myObj;
	
	public FenetreClient() throws InterruptedException
	{
		voitures = new LinkedList<VoitureClient>();

		objects = new LinkedList<ObjetClient>();
		
		rues = new ArrayList<RueClient>();
		
		boue = new ArrayList<RueClient>();
		
		boost = new ArrayList<RueClient>();
		
		fuel = new ArrayList<RueClient>();
				
		String str = infoRequest(true);
		
		VoitureClient v = new VoitureClient(myCar, str.replaceAll("\t.*$", ""));
		
		id = v.getId();
		
		voitures.add(v);
		
		try
		{
			str = connect("partie", "id="+id+"&new=1&name=Un%20Nom%20De%20Malade");
			/*
			str = connect("partie", "id="+id+"&connect=1&idPartie=0");
			*/
			System.out.println(str);
			makeRoad(str.replaceAll("^[^\t]*\t", ""));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1320, 720);

		this.setTitle(""+id);

		this.addKeyListener(new ClientKeyListener(this));

		this.setVisible(true);

		while(true)
		{
			
			String info = infoRequest();
			updateAllCars(info);
			this.validate();
			this.repaint();
			Thread.sleep(1000L/30);
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException
	{
		new FenetreClient();
	}

	private void makeRoad(String str)
	{
		startLine = new StartLine(str.replaceAll("\t.*$", ""));
		str = str.replaceAll("^[^\t]*\t", "");
		System.out.println(str);
		int nbRoute = Integer.parseInt(str.replaceAll("\t.*$", ""));
		str = str.replaceAll("^[^\t]*\t", "");
		String types = str.replaceAll("\t.*$", "");
		str = str.replaceAll("^[^\t]*\t", "");
		String x1s = str.replaceAll("\t.*$", "");
		str = str.replaceAll("^[^\t]*\t", "");
		String x2s = str.replaceAll("\t.*$", "");
		str = str.replaceAll("^[^\t]*\t", "");
		String y1s = str.replaceAll("\t.*$", "");
		str = str.replaceAll("^[^\t]*\t", "");
		String y2s = str.replaceAll("\t.*$", "");
		str = str.replaceAll("^[^\t]*\t", "");
		String tailles = str.replaceAll("\t.*$", "");
		
		for(int i = 0 ; i < nbRoute ; i++)
		{
			String type = types.replaceAll(";.*$", "");
			types = types.replaceAll("^[^;]*;", "");
			
			int x1 = Integer.parseInt(x1s.replaceAll(";.*$", ""));
			x1s = x1s.replaceAll("^[^;]*;", "");
			
			int x2 = Integer.parseInt(x2s.replaceAll(";.*$", ""));
			x2s = x2s.replaceAll("^[^;]*;", "");
			
			int y1 = Integer.parseInt(y1s.replaceAll(";.*$", ""));
			y1s = y1s.replaceAll("^[^;]*;", "");
			
			int y2 = Integer.parseInt(y2s.replaceAll(";.*$", ""));
			y2s = y2s.replaceAll("^[^;]*;", "");
			
			int taille = Integer.parseInt(tailles.replaceAll(";.*$", ""));
			tailles = tailles.replaceAll("^[^;]*;", "");
			
			if(type.equals("RUE"))
			{
				rues.add(new RueClient(RueClient.RUE, x1, x2, y1, y2, taille));
			}
			else if(type.equals("CARBURANT"))
			{
				fuel.add(new RueClient(RueClient.FUEL, x1, x2, y1, y2, taille));
			}
			else if(type.equals("BOOST"))
			{
				boost.add(new RueClient(RueClient.BOOST, x1, x2, y1, y2, taille));
			}
			else if(type.equals("BOUE"))
			{
				boue.add(new RueClient(RueClient.BOUE, x1, x2, y1, y2, taille));
			}
		}
	}
	
	public static BufferedImage toBufferedImage(String str)
	{
		return toBufferedImage(new ImageIcon(FenetreClient.class.getResource("/images/"+str+".png")).getImage());
	}
	
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

	public String makeGameArg()
	{
		String str = "id="+id;
		str += "&avancer=" + (avancer?"1":"0") ;
		str += "&reculer=" + (reculer?"1":"0") ;
		str += "&gauche=" + (tournerGauche?"1":"0") ;
		str += "&droite=" + (tournerDroite?"1":"0") ;
		str += "&useObj=" + (useObj?"1":"0") ;
		useObj = false;
		return str;
	}

	public String infoRequest(boolean first)
	{
		try
		{
			if(first)
			{
				String str = connect("nouveau","id="+id+"&pseudo=client");
//				System.out.println(str);
				return str;
			}
			else
			{
				String str = connect("jeu",makeGameArg()); 
//				System.out.println(str);
				return str;
			}
		}
		catch (IOException e)
		{
			JDialog dia = new JDialog(this);
			e.printStackTrace();
			dia.setModal(true);
			dia.add(new JLabel("Connection perdue"));
			dia.setSize(200, 50);
			dia.setVisible(true);
			System.exit(1);
		}
		return null;
	}

	public String infoRequest()
	{
		return infoRequest(false);
	}

	private String connect(String path, String arg) throws IOException
	{
		String ur="http://192.168.1.16:8080/test/"+path;
		URL url = new URL(ur);
		URLConnection  conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(arg);
		writer.flush();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String reponse=reader.readLine();
		return reponse;
	}

	public void setAvancer(boolean avancer)
	{
		this.avancer = avancer;
	}

	public void setReculer(boolean reculer)
	{
		this.reculer = reculer;
	}

	public void setTournerDroite(boolean tournerDroite)
	{
		this.tournerDroite = tournerDroite;
	}

	public void setTournerGauche(boolean tournerGauche)
	{
		this.tournerGauche = tournerGauche;
	}
	
	public void setUseObj(boolean useObj)
	{
		this.useObj = useObj;
	}

	private BufferedImage labelToBufferedImage(String label)
	{
		if(label.equals("CHAMPI"))
		{
			return champignon;
		}
		else if(label.equals("BANANA"))
		{
			return banana;
		}
		else if(label.equals("GIVER"))
		{
			return giver;
		}
		else
		{
			return null;
		}
	}
	
//	private void udateACar(String str, boolean isMe)
//	{
//		VoitureClient v;
//		long vId;
//		if(isMe)
//		{
//			v = new VoitureClient(myCar,str);
//			id=v.getId();
//			vId = id;
//		}
//		else
//		{
//			v = new VoitureClient(foeCar,str);
//			vId=v.getId();
//		}
//		
//		VoitureClient vBase = voitures.get(vId);
//		
//		if(vBase==null)
//		{
//			voitures.put(vId, v);
//			this.add(v);
//			this.validate();
//		}
//		else
//		{
//			this.add(vBase);
//			vBase.setPos(str);
//		}
//	}
	
	public void updateAllCars(String str)
	{
		String[] strs = token(str, '\t');
		
		voitures.clear();
		
		int nbVoiture= Integer.parseInt(strs[0]);
		
		
		strs = token(strs[1], '\t');
		
		int classement = Integer.parseInt(strs[0]);
		System.out.println(classement);
		
		strs = token(strs[1], '\t');
		VoitureClient v = new VoitureClient(myCar, strs[0]);
		voitures.add(v);
		centerX = v.getX()-this.getWidth()/2;
		centerY = v.getY()-this.getHeight()/2;
		
		for (int i = 1; i < nbVoiture; i++)
		{
			strs = token(strs[1], '\t');
			voitures.add(new VoitureClient(foeCar, strs[0]));
		}
		strs = token(strs[1], '\t');
		myObj = labelToBufferedImage(strs[0]);
		updateAllObjs(strs[1]);
	}
	
	public void updateAllObjs(String str)
	{
		String[] strs = token(str, '\t');
		
		objects.clear();
		
		int nbObjet= Integer.parseInt(strs[0]);
		
		for (int i = 0; i < nbObjet; i++)
		{
			strs = token(strs[1], '\t');
			
			String[] info = token(strs[0], ';');
			String label = info[0];
			info = token(info[1], ';');
			int x = Integer.parseInt(info[0]);
			info = token(info[1], ';');
			int y = Integer.parseInt(info[0]);
			BufferedImage img = labelToBufferedImage(label);
			ObjetClient obj = new ObjetClient(x, y, img);
			objects.add(obj);
		}
		
//		updateAllObjs(strs[1]);
	}
	
	private static String[] token(String str , char c)
	{
		String[] strs = new String[2];
		int index = str.indexOf(c);
		strs[0] = str.substring(0, index);
		strs[1] = str.substring(index+1);
		return strs;
	}
	
	public void paint(Graphics arg0)
	{
		Graphics g;
		Image img=createImage(getWidth(), getHeight());
		g=img.getGraphics();
		
		for(RueClient rue : boue)
		{
			rue.paintComponent(centerX, centerY, g);
		}
		
		for(RueClient rue : fuel)
		{
			rue.paintComponent(centerX, centerY, g);
		}
		
		for(RueClient rue : rues)
		{
			rue.paintComponent(centerX, centerY, g);
		}
		
		for(RueClient rue : boost)
		{
			rue.paintComponent(centerX, centerY, g);
		}
		
		for(RueClient rue : rues)
		{
			rue.paintCenter(centerX, centerY, g);
		}
		
		startLine.paintComponent(centerX, centerY, g);
		
		for(ObjetClient obj : objects)
		{
			obj.paintComponent(centerX, centerY, g);
		}
		
		for(VoitureClient v : voitures)
		{
			v.paintComponent(centerX, centerY, g);
		}
		
		g.drawImage(myObj, 10, 10, null);
		
		arg0.drawImage(img, 0 , 0 , this);
	}
}
