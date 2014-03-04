package zappos2;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.*;




public class ZapposChallenge2 {
	
	private final String USER_AGENT = "Mozilla/5.0";
	public static ArrayList<Integer> SavedProducts = new ArrayList<Integer>();
	public static ArrayList<Float> SavedPrice = new ArrayList<Float>();
	static String SavedURL ;
	public static String SavedEmailID;
	
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
	
	static boolean tryParseInt(String value)  
	{  
	     try  
	     {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch(NumberFormatException nfe)  
	      {  
	    	  System.out.println("Please enter a valid integer value");
	          return false;  
	      }  
	}
	private static final Pattern rfc2822 = Pattern.compile(
	        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
	);
	
	public static void main(String[] args) throws Exception {
		 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println("Enter the product category to be searched");
		//String myInput= br.readLine();
		ZapposChallenge2 http = new ZapposChallenge2();

		ArrayList<String> productName = new ArrayList<String>();
		ArrayList<Float> Price = new ArrayList<Float>();
		ArrayList<String> ProductUrl = new ArrayList<String>();
		ArrayList<Integer> ProductID = new ArrayList<Integer>();
		ArrayList<String> urls = new ArrayList<String>();
		
		//urls.add("http://api.zappos.com/Search?term="+myInput+"&key=a73121520492f88dc3d33daf2103d7574f1a3166");
		SavedURL = "http://api.zappos.com/Search?term=beauty&key=a73121520492f88dc3d33daf2103d7574f1a3166";
		urls.add("http://api.zappos.com/Search?term=beauty&key=a73121520492f88dc3d33daf2103d7574f1a3166");
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
		
		System.out.println("Please enter the product ID from the above listed items you want to mark for the price drop notification:");
		String temp = br.readLine();
		
		
		
		String[] parts = temp.split(" ");
		int part = 0;
		for(int k=0;k<parts.length;k++){
			if(tryParseInt(parts[k]))
			part = Integer.parseInt(parts[k]);
			if(ProductID.contains(part)){
			SavedProducts.add(part);
			SavedPrice.add(Price.get(ProductID.indexOf(part)));
			}
			else
				System.out.println("Invalid productID entered. Please choose a valid product");
		}
			//SavedProducts.add(8182099);
			//SavedPrice.add((float) 100.0);
		if(SavedProducts.size()>0){
			
			System.out.println("please enter your email ID to which price notification has to be sent:");
			SavedEmailID = br.readLine();
			

			if (rfc2822.matcher(SavedEmailID).matches()) {
				Alarm alarm = new Alarm();
				//throw new Exception("Invalid address");
			}
			else {
				System.out.println("Invalid Email address format");
				System.out.println("Please try again with a valid email ID");
			}
		
			
		}
		
			
		br.close();
		//System.out.println(SavedProducts);
		//System.out.println(SavedPrice);
		
		
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
		//System.out.println("Testing 1 - Send Http GET request");
		//for(int j=0;j<urls.size();j++)
		ArrayList<Float> Price = new ArrayList<Float>();
		ArrayList<Integer> ProductID = new ArrayList<Integer>();
		ArrayList<String> productName = new ArrayList<String>();
		http1.sendGet2(productName,Price,ProductID);
		
		for(int i=0;i<Price.size();i++){
			//System.out.println("Price of the product"+productName.get(i)+"dropped by more thane 20 % of original price");
			//System.out.println("Its current price is :"+Price.get(i));
			
			SendMail obj = new SendMail();
			obj.sendmail(SavedEmailID,productName.get(i),Price.get(i) );
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
		//System.out.println("\nSending 'GET' request to URL : " + SavedURL);
		//System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 	
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
				//System.out.println("I am sending message");
				if(SavedProducts.contains(productID)){
					//System.out.println((0.8)*SavedPrice.get(SavedProducts.indexOf(productID)));
					if((0.8)*SavedPrice.get(SavedProducts.indexOf(productID))>= Price){
						
						//System.out.println("I am sending message");
						pPrice.add(Price);
				        //pName.add(prName);
				        //pUrl.add(producturl);
				        pID.add(productID);
						
					}
						
							
						
					
				}
				
				
				
		    }
		

	}


}
 



