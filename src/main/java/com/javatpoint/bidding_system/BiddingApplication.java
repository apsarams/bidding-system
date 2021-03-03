package com.javatpoint.bidding_system;


import java.io.IOException;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication				////similar to @Configuration @EnableAutoConfiguration @ComponentScan
@RestController
public class BiddingApplication 
{
	public static String bidderA = "http://localhost:8081";
	public static String bidderB = "http://localhost:8082";
	public static String bidderC = "http://localhost:8083";
	private static String bidrequest;
	AuctionResponse auction = new AuctionResponse();
	public static void main(String[] args)  
	{    
		SpringApplication.run(BiddingApplication.class, args);
	}
	
	@RequestMapping("/{id}")
	public String handlingRequest(@PathVariable("id") String id, @RequestParam Map<String, String> bidder) throws IOException
	{
		BidRequest newbid = new BidRequest();
		newbid.setId(id);
		newbid.setAttributes(bidder);
		bidrequest = BidRequest.toJson(newbid);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(bidrequest, headers);
		
		int bidA = responseForBid(bidderA, request, String.class);
		int bidB = responseForBid(bidderB, request, String.class);
		int bidC = responseForBid(bidderC, request, String.class);
		
		String largestBid = auction.getLargestBid(bidA, bidB, bidC);
		return largestBid;
	}
	public int responseForBid(String url, HttpEntity<String> request,Class<String> cls) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(url, request, String.class);
		int bidvalue = auction.getAuctionValue(response); 
		return bidvalue ;
	}
	
	@GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}
