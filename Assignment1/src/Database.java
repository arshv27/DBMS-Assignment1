import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class Database
{
	protected void ReadFile(String s1) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(s1));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			for (String str : values) {
				System.out.println(str);
			}
		}
		br.close();
	}
	public static void main(String[] args) throws IOException
	{
		Database D1= new Database();
		D1.ReadFile("Metadata.txt");
	}


}
