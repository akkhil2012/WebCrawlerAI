package com.web.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CrawlController {

	protected PageFetcher pageFetcher;
	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}

	private final CrawlConfig config;
	protected Parser parser;

	public PageFetcher getPageFetcher() {
		return pageFetcher;
	}

	public void setPageFetcher(PageFetcher pageFetcher) {
		this.pageFetcher = pageFetcher;
	}

	public CrawlController(PageFetcher pageFetcher, CrawlConfig config) {
		this.config = config;

		File folder = new File(config.getCrawlStorageFolder());

		if (!folder.exists()) {
			if (folder.mkdirs())
				System.out.println("Ceated intermediate folder");
			else
				System.out.println("Exception while creating Folder......");
		}

		this.pageFetcher = pageFetcher;
		try {
			this.parser = new Parser(config);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void start(WebCrawler myWebCrawler, int numberOfThreads) {
		final List<Thread> threads = new ArrayList<>();
		final List<WebCrawler> crawlers = new ArrayList<>();
		for (int i = 1; i <= numberOfThreads; i++) {
			MyCrawler crawler = new MyCrawler();
			Thread thread = new Thread(crawler, "Crawler " + i);
			crawler.setThread(thread);
			crawler.init(i, this);
			thread.start();
			crawlers.add(crawler);
			threads.add(thread);
			System.out.println(" Started Crawler....");
		}

		final CrawlController controller = this;
		Thread monitorThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//System.out.println(" Inside run method..................");
			}
		});

		monitorThread.start();

	}

}
