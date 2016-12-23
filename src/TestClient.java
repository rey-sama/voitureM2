import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class TestClient
{
	
	
	public static void main(String[] args) throws IOException
	{
		new TestClientGraphique();
//		TestClient tc = new TestClient();
//		String txt = tc.connect("nouveau","pseudo=rey");
//		Information info = new Information(txt);
//		Long id = info.id;
//		
//		System.out.println(tc.connect("jeu","avancer=1&id="+id));
//		System.out.print(tc.connect("jeu","avancer=1&id="+id));
	}
	
	private String connect(String path, String arg) throws IOException
	{
		String ur="http://localhost:8080/test/"+path;
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
	
	private static class Information
	{
		public long id;
		public int x;
		public int y;
		public int orientation;
		
		public Information(String text)
		{
			String var = null;
			String rest = text;
			var = rest.substring(0, rest.indexOf(";"));
			rest = rest.substring(rest.indexOf(";")+1);
			id = Long.parseLong(var);
			
			var = rest.substring(0, rest.indexOf(";"));
			rest = rest.substring(rest.indexOf(";")+1);
			x = Integer.parseInt(var);
			
			var = rest.substring(0, rest.indexOf(";"));
			rest = rest.substring(rest.indexOf(";")+1);
			y = Integer.parseInt(var);
			
			orientation = Integer.parseInt(rest);
		}
	}
}
