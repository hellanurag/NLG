import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class TemplateGenerator {


	public static void main(String[] args) throws IOException {
		// open file input stream
		BufferedReader reader = new BufferedReader(new FileReader(
				"licensedata.csv"));

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
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        Realiser realiser = new Realiser(lexicon);
		
        String greet = "Greetings, \n\n";
        
        //Random customerid
        int ci = ThreadLocalRandom.current().nextInt(1, 6);
        
        SPhraseSpec p1 = nlgFactory.createClause();
        p1.setSubject("You");
        p1.setVerb("acquire");
        p1.setObject("product " + custList.get(0).getProductname());
        p1.setFeature(Feature.TENSE, Tense.PAST);
        p1.setFeature(Feature.PERFECT, true);
        p1.addComplement(custList.get(ci).getWeeksfrompurchase() + " weeks ago");
        p1.addComplement("and " + custList.get(ci).getActiveusers() + " people from your organization have leveraged it last week."); 
        
        //String output1 = realiser.realiseSentence(p1); // Realiser created earlier.
        //System.out.println(output1);
        
        SPhraseSpec p2 = nlgFactory.createClause();
        p2.setSubject(custList.get(ci).getCustomername() + " (ID # " + custList.get(ci).getCustomerid() + ")");
        p2.setVerb("utilize");
        p2.setObject(custList.get(ci).getPercUtil() + "% of its " + custList.get(ci).getTotallicenses() + " available licenses");
        p2.setFeature(Feature.TENSE, Tense.PRESENT);
        p2.setFeature(Feature.PERFECT, true);
                
        //String output2 = realiser.realiseSentence(p2); // Realiser created earlier.
        //System.out.println(output2);
        
        String utilavg;
        if(custList.get(ci).getPercUtil() >= 70.0)
        {
        utilavg = "This utilization rate is more than the industry average. ";
        }
        else if((custList.get(ci).getPercUtil() >= 50.0) && (custList.get(ci).getPercUtil() <= 70.0))
        {
        	utilavg = "This utilization rate about the same as the industry average. ";	
        }
        else
        {
        	utilavg = "This utilization rate is less than the industry average. ";	
        }
        
        String percusage;
        if(custList.get(ci).getPercUtil() >= 85.0)
        {
        	percusage = "We have seen a surge based on the weekly active user base number. Would you like to obtain more licenses? \n\n";
        }
        else if(custList.get(ci).getPercUtil() <= 45.0)
        {
        	percusage = "We have seen a decline based on the weekly active user base number.Would you like to schedule an interview to improve the product usage? \n\n";	
        }
        else
        {
        	percusage = "We have seen that the utilization ratio is stable based on the weekly active user base number. \n\n";	
        }
        
        String reco = null;
        if(custList.get(ci).getProductid() == 1)
        {
        	reco = "Product B also seems to be good fit for" + custList.get(ci).getCustomername() + ". Would you like to schedule a call to know more about it? \n";
        }
        else if(custList.get(ci).getProductid() == 2)
        {
        	reco = "Product C also seems to be good fit for" + custList.get(ci).getCustomername() + ". Would you like to schedule a call to know more about it? \n";	
        }
        else if(custList.get(ci).getProductid() == 3)
        {
        	reco = "Product A also seems to be good fit for" + custList.get(ci).getCustomername() + ". Would you like to schedule a call to know more about it? \n";	
        }
        
        String regards = "\nRegards, \n" + "Your vendor.";
        
        DocumentElement s1 = nlgFactory.createSentence(p1);
        DocumentElement s2 = nlgFactory.createSentence(p2);
        
        DocumentElement par1 = nlgFactory.createParagraph(Arrays.asList(s1, s2));
        
        //DocumentElement section = nlgFactory.createSection("Sample Template:");
        DocumentElement section = nlgFactory.createSection("");
        section.addComponent(par1);
        String output = realiser.realise(section).getRealisation();
        
        String finaloutput = greet + output + utilavg + percusage + reco + regards;
        
        System.out.println(finaloutput);
		
	}
}