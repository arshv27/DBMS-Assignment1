import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class Database
{
	private ArrayList<String[]> DBarray = new ArrayList<String[]>();
	protected void ReadFile(String s1) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(s1));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			String[] row = new String[3];
			for (int i=0; i<values.length; i++) {
				row[i%3]=values[i];
				if(i%3==2) {
					DBarray.add(row);
				}
			}
		}

		br.close();
	}
	public static void main(String[] args) throws IOException{
		Database D1= new Database();
		D1.ReadFile("Metadata.txt");
	}


}
