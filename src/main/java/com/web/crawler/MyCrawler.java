package com.web.crawler;

import java.util.Set;
import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler {

	private Thread thread;

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp4|zip|gz))$");

	public boolean shouldVisit(Page referringPage, String url) {
		String href = url.toLowerCase();
		System.out.println(" URl to parse is " + href);
		return !FILTERS.matcher(href).matches()
				&& href.startsWith("https://www.ics.uci.edu/");
	}

	@Override
	public void visit(Page page) {
		String url = page.getUrl();
		System.out.println("URL:>>>; " + url);
		// / TO DO
		
		if(page.getParseData() instanceof HtmlParseData){
			
			HtmlParseData htmlParseData
			                    = (HtmlParseData) page.getParseData();
			
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			
			Set<WebURL> links = htmlParseData.getOutgoingUrls();

            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());
			
		}
		
		
	}

}
