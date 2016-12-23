package client;

public class Information
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