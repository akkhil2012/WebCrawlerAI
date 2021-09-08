package com.web.crawler;

public class Controller {

	public static void main(String args[]) {

		String crawlStorageFolder = "/data/crawl/root";
		int numberOfCrawlers = 2;

		CrawlConfig config = new CrawlConfig();
		// config.setCrawlStorageFolder(crawlStorageFolder);
		config.setCrawlStorageFolder(crawlStorageFolder);

		// Instantiate the controller for this crawl.
		PageFetcher pageFetcher = new PageFetcher(config);

		CrawlController crawlController = new CrawlController(pageFetcher,
				config);

		MyCrawler myCrawler = new MyCrawler();

		crawlController.start(myCrawler, numberOfCrawlers);

	}

}
