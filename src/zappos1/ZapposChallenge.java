package zappos1;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

class Products {

public String name;
public String price;
//	
//public void Products(String n, int p) {
//	name = n;
//	price = p;
//}
	
}
 
 

public class ZapposChallenge {
 
	private final String USER_AGENT = "Mozilla/5.0";
	
	public void printitems(ArrayList<String> pName,ArrayList<Float> pPrice, int n) {
		//int iterator = pPrice.size()/n;
		System.out.println(pName);
		System.out.println(pPrice);
		Random r = new Random();
		int  i=0;
		int randomIndex=0;
		System.out.println(pName.size());
		int size = pName.size();
		int rounds = (2*size)/3;
		System.out.println(rounds);
		for(int j =0;j<rounds;j++){
		for (i = 0; i < n; i++) {
			randomIndex = r.nextInt(pName.size());
			System.out.println(pName.get(randomIndex)+"   "+pPrice.get(randomIndex));
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
	}
			
	
	}
 
	static boolean tryParseInt(String value)  
	{  
	     try  
	     {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch(NumberFormatException nfe)  
	      {  
	          return false;  
	      }  
	}
	static boolean tryParseFloat(String value)  
	{  
	     try  
	     {  
	         Float.parseFloat(value);  
	         return true;  
	      } catch(NumberFormatException nfe)  
	      {  
	          return false;  
	      }  
	}
	public static void main(String[] args) throws Exception {
 
		int no_of_items =0;
		float cost =0;
		float Individual_Cost;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter No of items:");
		String myInput= br.readLine();
		if(tryParseInt(myInput))
		{  
			no_of_items = Integer.parseInt(myInput); //We know it is safe to parse.
		}
		//no_of_items = Integer.parseInt(br.readLine());
		System.out.println("Enter total price:");
		String  myInput1 = br.readLine();
		if(tryParseFloat(myInput1))
		cost = Float.parseFloat(myInput1);
		br.close();
		
		Individual_Cost= cost/no_of_items;
		
		
		ZapposChallenge http = new ZapposChallenge();
		//ArrayList<ArrayList <String>> productName = new ArrayList<ArrayList<String>> (5);
		//ArrayList<ArrayList <Float>> Price = new ArrayList<ArrayList<Float>> ();
		
		ArrayList<String> productName = new ArrayList<String>();
		ArrayList<Float> Price = new ArrayList<Float>();
		ArrayList<String> urls = new ArrayList<String>();
		urls.add("http://api.zappos.com/Search?term=boots&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=beauty&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=clothing&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=watches&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=jewelry&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=men&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=eyewear&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=women&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=kids&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		//urls.add("http://api.zappos.com/Search?term=accessories&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		//urls.add("http://api.zappos.com/Search?term=new%20arrivals&key=a73121520492f88dc3d33daf2103d7574f1a3166");

		
		System.out.println("Testing 1 - Send Http GET request");
		for(int j=0;j<urls.size();j++)
		http.sendGet(productName,Price,urls.get(j),Individual_Cost);
 
		System.out.println(productName);
		System.out.println(Price);
		http.printitems(productName, Price, no_of_items);
 
	}
 
	// HTTP GET request
	private void sendGet(ArrayList<String> pName, ArrayList<Float> pPrice, String url,float cost) throws Exception {
 
		
		//String url = "http://api.zappos.com/Search?term=boots&key=a73121520492f88dc3d33daf2103d7574f1a3166d";
		
	
	
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		//System.out.println(response.toString());
 
	
		JSONObject obj1 = new JSONObject(response.toString());
		JSONArray n = obj1.getJSONArray("results");
		Products []p = new Products[n.length()];
		
		int i = 0;
		String prName;
		float Price=0;
		String temp;
		for(i=0; i<n.length(); i++){
//		        p[i].price = n.getJSONObject(i).getString("price");
		        //p[i].name = n.getJSONObject(i).getString("productName");
				temp = n.getJSONObject(i).getString("price");
				temp = temp.substring(1);
				Price = Float.parseFloat(temp);
				prName = n.getJSONObject(i).getString("productName");
				
				if (Price > cost-10 && Price<=cost) {
				
				pPrice.add(Price);
		        pName.add(prName);
				}
		    }

	}
	
 
}