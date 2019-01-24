import java.awt.event.MouseWheelEvent;
import java.io.*;
import java.util.ArrayList;

public class Database
{
	private ArrayList<String[]> MetadataArray = new ArrayList<String[]>();
	private ArrayList<String[]> DBArray = new ArrayList();
	protected void ReadMetaData(String s1, String s2) throws IOException {
		//System.out.println("Reading " + s1);
		BufferedReader br = new BufferedReader(new FileReader(s1));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(s2);
			String[] row = new String[3];
			for (int i=0; i<values.length; i++) {
				row[i%3]=values[i];
				if(i%3==2) {
					MetadataArray.add(row);
				}
			}
		}
//		for (int i=0; i<MetadataArray.size(); i++) {
//			for (int j=0; j<3; j++) {
//				System.out.print(MetadataArray.get(i)[j]+" ");
//			}
//			System.out.println();
//		}
		br.close();
	}
	protected void ReadFile(String s1) throws IOException
	{
		FileInputStream fis = new FileInputStream(s1);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		while ((line = br.readLine()) != null) {
			int k=0;
			String[] Arr = new String[MetadataArray.size()];
			for(int i=0; i<MetadataArray.size(); i++) {
				String a = line.substring(k,k+Integer.parseInt(MetadataArray.get(i)[2]));
				k+=Integer.parseInt(MetadataArray.get(i)[2]);
				Arr[i]=a;
			}
			DBArray.add(Arr);
		}
		br.close();
	}

	protected void printData() {
		for (int i=0; i<MetadataArray.size(); i++) {
			System.out.print("Field: "+MetadataArray.get(i)[0]+" type: "+MetadataArray.get(i)[1]+" size: "+MetadataArray.get(i)[2]);
			System.out.println();
		}
		System.out.println();
		System.out.println("Finished reading data description file...");
		System.out.println();
		System.out.println("The data file contains the following records: ");
		for (int i=0; i<DBArray.size(); i++)  {
			for (int j=0; j<DBArray.get(i).length; j++) {
				System.out.print(DBArray.get(i)[j] +" ");
			}
			System.out.println();
		}
	}

	protected void findMax(String s) {
		int index = -1;
		String type=null;

		for (int i=0; i<MetadataArray.size(); i++) {
			if (MetadataArray.get(i)[0].equals(s)) {
				index=i;
				type=MetadataArray.get(i)[1];
				break;
			}
		}
		if(index==-1) {
			System.out.println("--- Error: Field name not found!");
		}
		else{
			if (type.equals("I")||type.equals("D")||type.equals("F")){
				double max =0;
				for (int i=0; i<DBArray.size(); i++) {
					if (i==0) {
						max=Double.parseDouble(DBArray.get(i)[index]);
					}
					else{
						if (Double.parseDouble(DBArray.get(i)[index])>max) {
							max=Double.parseDouble(DBArray.get(i)[index]);
						}
					}
				}
				System.out.println();
				System.out.println("Find max value in the field: " + s);
				System.out.println("Max= "+ max);
			}
			else{
				String max = null;
				for (int i=0; i<DBArray.size(); i++) {
					if (i==0) {
						max=DBArray.get(i)[index];
					}
					else{
						if (DBArray.get(i)[index].compareTo(max)>0) {
							max=DBArray.get(i)[index];
						}
					}
				}
				System.out.println();
				System.out.println("Find max value in the field: " + s);
				System.out.println("Max= "+ max);
			}
		}
	}
	public static void main(String[] args) throws IOException{
		Database D1= new Database();
		D1.ReadMetaData("Metadata.txt", ",");
		D1.ReadFile("Db1.txt");
		D1.printData();
		D1.findMax(args[0]);
	}
}
