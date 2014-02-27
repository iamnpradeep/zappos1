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

public class HttpURLConnectionExample {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	public static void main(String[] args) throws Exception {
 
		int n;
		float price;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter n:");
		n = Integer.parseInt(br.readLine());
		System.out.println("Enter price:");
		price = Float.parseFloat(br.readLine());
		br.close();
		
		
		HttpURLConnectionExample http = new HttpURLConnectionExample();
		ArrayList<ArrayList <String>> productName = new ArrayList<ArrayList<String>> (5);
		ArrayList<ArrayList <Float>> Price = new ArrayList<ArrayList<Float>> ();
		
		//ArrayList<String> productName = new ArrayList<String>();
		//ArrayList<Float> Price = new ArrayList<Float>();
		ArrayList<String> urls = new ArrayList<String>();
		urls.add("http://api.zappos.com/Search?term=boots&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=beauty&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=clothing&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=watches&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=jewelry&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		urls.add("http://api.zappos.com/Search?term=men&key=a73121520492f88dc3d33daf2103d7574f1a3166");

		
		System.out.println("Testing 1 - Send Http GET request");
		for(int j=0;j<urls.size();j++)
		http.sendGet(productName[j], Price.get(j),urls.get(j));
 
		//System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();
		System.out.println(productName.get(1));
		System.out.println(Price.get(1));
 
	}
 
	// HTTP GET request
	private void sendGet(ArrayList<String> pName, ArrayList<Float> pPrice, String url) throws Exception {
 
		
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
				
				if (Price > 40) {
				
				pPrice.add(Price);
		        pName.add(prName);
				}
		    }
		//int a = obj1.getInt("age");
//		for (i = 0; i < n.length(); i++) {
//			System.out.println(p[i].price);
//			System.out.println(p[i].name);// prints "Alice 20"
//		}
		
		//System.out.println(Price);
		//System.out.println(productName);
	}
	
 
	// HTTP POST request
	private void sendPost() throws Exception {
 
		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
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
		System.out.println(response.toString());
 
	}
 
}