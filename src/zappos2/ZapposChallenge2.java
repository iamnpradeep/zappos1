package zappos2;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import zappos1.JSONArray;
import zappos1.JSONObject;



public class ZapposChallenge2 {
	
	private final String USER_AGENT = "Mozilla/5.0";
	public static ArrayList<Integer> SavedProducts = new ArrayList<Integer>();
	public static ArrayList<Float> SavedPrice = new ArrayList<Float>();
	static String SavedURL ;
	
	public void printitems(ArrayList<String> pName, ArrayList<Float> pPrice, ArrayList<String> pUrl, ArrayList<Integer> pID) {
		
		int size = pName.size();
		System.out.println();
		System.out.println("List of items in the searched category are:");
		
			for (int i = 0; i < size; i++) {
				System.out.println("Product Name: "+pName.get(i)+"   Price: "+pPrice.get(i));
				System.out.println("Product URL: "+pUrl.get(i));
				System.out.println("Product ID: "+pID.get(i));
				System.out.println();
				System.out.println();
				System.out.println();
			}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the product category to be searched");
		String myInput= br.readLine();
		ZapposChallenge2 http = new ZapposChallenge2();

		ArrayList<String> productName = new ArrayList<String>();
		ArrayList<Float> Price = new ArrayList<Float>();
		ArrayList<String> ProductUrl = new ArrayList<String>();
		ArrayList<Integer> ProductID = new ArrayList<Integer>();
		ArrayList<String> urls = new ArrayList<String>();
		
		urls.add("http://api.zappos.com/Search?term="+myInput+"&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		SavedURL = urls.get(0);
//		urls.add("http://api.zappos.com/Search?term=beauty&key=a73121520492f88dc3d33daf2103d7574f1a3166");
//		urls.add("http://api.zappos.com/Search?term=clothing&key=a73121520492f88dc3d33daf2103d7574f1a3166");
//		urls.add("http://api.zappos.com/Search?term=watches&key=a73121520492f88dc3d33daf2103d7574f1a3166");
//		urls.add("http://api.zappos.com/Search?term=jewelry&key=a73121520492f88dc3d33daf2103d7574f1a3166");
//		urls.add("http://api.zappos.com/Search?term=men&key=a73121520492f88dc3d33daf2103d7574f1a3166");
//		urls.add("http://api.zappos.com/Search?term=eyewear&key=a73121520492f88dc3d33daf2103d7574f1a3166");
//		urls.add("http://api.zappos.com/Search?term=women&key=a73121520492f88dc3d33daf2103d7574f1a3166");
//		urls.add("http://api.zappos.com/Search?term=kids&key=a73121520492f88dc3d33daf2103d7574f1a3166");
//		urls.add("http://api.zappos.com/Search?term=accessories&key=a73121520492f88dc3d33daf2103d7574f1a3166");
//		urls.add("http://api.zappos.com/Search?term=new%20arrivals&key=a73121520492f88dc3d33daf2103d7574f1a3166");

		
		System.out.println("Testing 1 - Send Http GET request");
		for(int j=0;j<urls.size();j++)
		http.sendGet(productName,Price,ProductUrl,ProductID,urls.get(j));
		
		http.printitems(productName, Price, ProductUrl, ProductID);
		
		System.out.println("Please enter the product ID you want to save:");
		String temp = br.readLine();
		
		
		
		String[] parts = temp.split(" ");
		int part;
		for(int k=0;k<parts.length;k++){
			
			part = Integer.parseInt(parts[k]);
			if(ProductID.contains(part))
			SavedProducts.add(part);
			SavedPrice.add(Price.get(ProductID.indexOf(part)));
		}
			
		br.close();
		System.out.println(SavedProducts);
		System.out.println(SavedPrice);
		
		
	}
		
 
	// HTTP GET request
	private void sendGet(ArrayList<String> pName, ArrayList<Float> pPrice,ArrayList<String> pUrl,ArrayList<Integer> pID, String url) throws Exception {
 
		
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
		
		
		int i = 0;
		String prName;
		float Price=0;
		String temp;
		String producturl;
		int productID;
		String temp2;
		for(i=0; i<n.length(); i++){

				temp = n.getJSONObject(i).getString("price");
				temp = temp.substring(1);
				Price = Float.parseFloat(temp);
				prName = n.getJSONObject(i).getString("productName");
				producturl= n.getJSONObject(i).getString("productUrl");
				temp2= n.getJSONObject(i).getString("productId");
				productID = Integer.parseInt(temp2);
				
				
				pPrice.add(Price);
		        pName.add(prName);
		        pUrl.add(producturl);
		        pID.add(productID);
				
		    }

	}
	
	public void checkPrice() throws Exception{
		
		ZapposChallenge2 http1 = new ZapposChallenge2();
		System.out.println("Testing 1 - Send Http GET request");
		//for(int j=0;j<urls.size();j++)
		ArrayList<Float> Price = new ArrayList<Float>();
		ArrayList<Integer> ProductID = new ArrayList<Integer>();
		ArrayList<String> productName = new ArrayList<String>();
		http1.sendGet2(productName,Price,ProductID);
		for(int i=0;i<Price.size();i++){
			System.out.println("Price of the product"+productName.get(i)+"dropped by more thane 20 % of original price");
			System.out.println("Its current price is :"+Price.get(i));
		}
		
	}
	
	private void sendGet2(ArrayList<String> pName,ArrayList<Float> pPrice,ArrayList<Integer> pID)  throws Exception{
 
		URL obj = new URL(SavedURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + SavedURL);
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
		
		
		int i = 0;
		//String prName;
		float Price=0;
		String temp;
		//String producturl;
		int productID;
		String temp2;
		for(i=0; i<n.length(); i++){
				temp = n.getJSONObject(i).getString("price");
				temp = temp.substring(1);
				Price = Float.parseFloat(temp);
				//prName = n.getJSONObject(i).getString("productName");
				//producturl= n.getJSONObject(i).getString("productUrl");
				temp2= n.getJSONObject(i).getString("productId");
				productID = Integer.parseInt(temp2);
				
				if(SavedProducts.contains(productID)){
					
					if((0.8)*SavedPrice.get(SavedProducts.indexOf(productID))>=(Price)){
						
						pPrice.add(Price);
				        //pName.add(prName);
				        //pUrl.add(producturl);
				        pID.add(productID);
						
					}
						
							
						
					
				}
				
				
				
		    }
		//return false;

	}


}
 



