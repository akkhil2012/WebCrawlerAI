package com.web.crawler;

import java.util.ArrayList;
import java.util.List;



public class WebCrawler implements Runnable {

	private Parser parser;
	private PageFetcher pageFetcher;
	protected CrawlController myController;
	protected int myId;

	public void init(int id, CrawlController crawlController) {
		this.myId = id;
		pageFetcher = crawlController.getPageFetcher();
		this.myController = crawlController;
		this.parser = crawlController.getParser();
	}

	@Override
	public void run() {
		List<String> assignedURLs = new ArrayList<>();
		assignedURLs
				.add("https://ibmsf.lightning.force.com/lightning/r/Case/5003p00002XcsAKAAZ/view");
		/*assignedURLs
				.add("https://ibmsf.lightning.force.com/lightning/r/Case/5003p00002YfYACAA3/view");
*/
		System.out.println(" url list size is " + assignedURLs.size());
		for (String url : assignedURLs) {
			processPage(url);
		}

	}

	public void addSeed(String pageUrl, int docID) {

	}

	private void processPage(String url) {
		PageFetchResult fetchResult = null;
		Page page = new Page(url);
		fetchResult = pageFetcher.fetchPage(url);

		// if(shouldVisit(page,url)){
		if (true) {
			visit(page);
		}

		/*System.out.println(" Visited the Page.........."
				+ fetchResult.getEntity().toString());*/
		/*
		 * Pass the result to Page and than parse
		 */
		
		System.out.println(" Parsing logic starts here.......");
		
		
		parser.parse(page,url);
		
		return;
	}

	public boolean shouldVisit(Page referringPage, String url) {

		return true;
	}

	public void visit(Page page) {
		// Do nothing by default
		// Sub-classed should override this to add their custom functionality

		String url = page.getUrl();
		System.out.println("URL:==========++++ " + url);
		
	
	}

	// implementtaion to do in Custom class: MyCrawler.java
	/*
	 * private boolean shouldVisit(Page referringPage, String url){ return true;
	 * }
	 */

}
