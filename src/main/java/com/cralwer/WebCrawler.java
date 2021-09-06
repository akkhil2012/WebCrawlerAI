package com.cralwer;

import java.util.ArrayList;
import java.util.List;

public class WebCrawler implements Runnable{

	private int batchReadSize;
	private PageFetcher pageFetcher;
	
	public void init(int id,CrawlController crawlController){
		this.pageFetcher = crawlController.getPageFetcher();
		this.batchReadSize = crawlController.getConfig().getBatchReadSize();
	}
	
	@Override
	public void run() {
		 List<WebURL> assignedURLs = new ArrayList<>(batchReadSize);
		 System.out.println("Starting crawler............>>>");
		 WebURL webURL1 = new WebURL();
		 webURL1.setUrl("https://en.wikipedia.org/wiki/Solan#Demographics");
		 
		 WebURL webURL2 = new WebURL();
		 webURL1.setUrl("https://en.wikipedia.org/wiki/Solan#Education/");
		
		 
		 assignedURLs.add(webURL1);
		 assignedURLs.add(webURL2);
		 
		 for(WebURL curURL : assignedURLs){
			processPage(curURL); 
		 }
	}
	
	private void processPage(WebURL curURL){
		PageFetchResult fetchResult = null;
		
		fetchResult = pageFetcher.fetchPage(curURL);
		System.out.println("  =================>......." + fetchResult.getEntity().toString());
		
		//parser.parse(page, curURL.getURL());
		//int statusCode = fetchResult.getStatusCode();
	}
	
	
	
	
	
	

}


