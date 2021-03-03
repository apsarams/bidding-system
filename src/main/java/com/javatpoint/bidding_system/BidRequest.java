package com.javatpoint.bidding_system;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class BidRequest {
	 private Map<String, String> attributes = new HashMap <String, String>();
	 private String id;	
	 public void setId(String id)
	 {
		this.id = id;
	 }
	 public void setAttributes(Map<String, String> attr)
	 {
		 this.attributes= attr;
	 }
	 public Map<String, String> getAttributes()
     {
		 return this.attributes;
     }
     public String getId()
     {
    	 return this.id;
     }
     public static String toJson(BidRequest br)
     {
    	 JSONObject json = new JSONObject();
    	 json.put("id", br.getId());
    	 json.put("attributes", br.getAttributes());
    	 return json.toString();
     }
}