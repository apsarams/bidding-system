package com.javatpoint.bidding_system;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuctionResponse {
	public int getAuctionValue(String resp) throws IOException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(resp);
		int bid = root.get("bid").asInt();
		return bid;
	}
	public String getLargestBid(int a, int b, int c)
	{
		String largest;
		if(a>=b && a>=c)  
			largest = "a:" + a;
		else if (b>=a && b>=c)
			largest = "b:" + b;
		else
			largest = "c:" + c;
		
		return largest;
	}

}
