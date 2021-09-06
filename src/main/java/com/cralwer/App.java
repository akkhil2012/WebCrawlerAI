package com.cralwer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        String crawlStorageFolder = "C:\\crawl";
        int numberOfCrawlers = 2;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        // Instantiate the controller for this crawl.
       // PageFetcher pageFetcher = new PageFetcher(config);
        PageFetcher pageFetcher = new PageFetcher(config);
        
        CrawlController controller;
		try {
			controller = new CrawlController(config,pageFetcher);
		

        // For each crawl, you need to add some seed urls. These are the first
        // URLs that are fetched and then the crawler starts following links
        // which are found in these pages
        controller.addSeed("https://en.wikipedia.org/wiki/Solan#Demographics");
        controller.addSeed("https://en.wikipedia.org/wiki/Solan#Education/");
        
        CrawlController.WebCrawlerFactory<CrawlerImpl> factory = CrawlerImpl::new;
        
        // Start the crawl. This is a blocking operation, meaning that your code
        // will reach the line after this only when crawling is finished.
        controller.start(factory, numberOfCrawlers);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
