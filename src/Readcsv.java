import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Readcsv {


	public static void main(String[] args) throws IOException {
		// open file input stream
		BufferedReader reader = new BufferedReader(new FileReader(
				"licensedata.csv"));
				//"C:\\Users\\anurag.reddy\\Documents\\GitHub\\NLG\\licensedata.csv"));

		// read file line by line
		String line = null;
		Scanner scanner = null;
		int index = 0;
		int iter = 0;
		List<customer> custList = new ArrayList<>();
		
		while ((line = reader.readLine()) != null) {
			//Skipping the header.
			if (iter == 0)
			{
				iter++;
				continue;
			}
			customer cust = new customer();
			scanner = new Scanner(line);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				String data = scanner.next();
				if (index == 0)
					cust.setUniqueid(Integer.parseInt(data));
				else if (index == 1)
					cust.setCustomerid(Integer.parseInt(data));
				else if (index == 2)
					cust.setCustomername(data);
				else if (index == 3)
					cust.setCustomerrep(data);
				else if (index == 4)
					cust.setProductid(Integer.parseInt(data));
				else if (index == 5)
					cust.setProductname(data);
				else if (index == 6)
					cust.setTotallicenses(Integer.parseInt(data));
				else if (index == 7)
					cust.setActiveusers(Integer.parseInt(data));
				else if (index == 8)
					cust.setWeeksfrompurchase(Integer.parseInt(data));
				else
					System.out.println("invalid data::" + data);
				index++;
			}
			index = 0;
			custList.add(cust);
			iter++;
		}
		
		//close reader
		reader.close();
		
		/*
		public List<customer> getCustomerList(){
			return custList;
		}
		*/
		
		//System.out.println(custList.get(0).getUniqueid());
		//System.out.println(custList.get(0).getPercUtil());
		
	}

}
