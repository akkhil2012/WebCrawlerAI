package com.cralwer;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.cralwer.CrawlController.WebCrawlerFactory;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;



public class CrawlController {

	protected PageFetcher pageFetcher;
	protected Frontier frontier;
	
	private final CrawlConfig config;
	
	public CrawlController(CrawlConfig config,PageFetcher pageFetcher) throws Exception{
		
		//config.validate();
		
		File folder = new File(config.getCrawlStorageFolder());
		
		if(!folder.exists()){
			if(folder.mkdir()){
				System.out.println(" Created Source Folder ");
			}else{
				throw new Exception(" Path doesn't exists... ");
			}
		}
		
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
        envConfig.setTransactional(true);
        envConfig.setLocking(true);
        envConfig.setLockTimeout(500l, TimeUnit.MILLISECONDS);
		
		File envHome = new File(config.getCrawlStorageFolder()+"/frontier");
		
		if(!envHome.exists()){
			if(envHome.mkdir()){
				System.out.println(" Created Frontier Folder......");
			}else{
				throw new Exception(" failed o create folder..");
			}
		}
		
		Environment env = new Environment(envHome, envConfig);
		
		frontier = new Frontier();
		//frontier = new Frontier(env,envConfig);
		this.config = config;
		this.pageFetcher = pageFetcher;
	}
	
	public PageFetcher getPageFetcher() {
        return pageFetcher;
    }
	
	 public CrawlConfig getConfig() {
	        return config;
	  }
	
	
	public void addSeed(String pageUrl){
		//addSeed(pageUrl,-1);
		
		WebURL webURL = new WebURL();
		frontier.schedule(webURL);
	}
	
	
	public interface WebCrawlerFactory<T extends WebCrawler>{
		T newInstance()  throws Exception;
	}
	
	
	 private static class DefaultWebCrawlerFactory<T extends WebCrawler>
     implements WebCrawlerFactory<T> {
     final Class<T> clazz;

     DefaultWebCrawlerFactory(Class<T> clazz) {
         this.clazz = clazz;
     }

     @Override
     public T newInstance() throws Exception {
         try {
             return clazz.newInstance();
         } catch (ReflectiveOperationException e) {
             throw e;
         }
     }
 }
	 

   
	 
	 public <T extends WebCrawler> void start(WebCrawlerFactory<T> crawlerFactory,
             int numberOfCrawlers) {
		 this.start(crawlerFactory, numberOfCrawlers, true);
	 }

	
	 protected <T extends WebCrawler> void start(final WebCrawlerFactory<T> crawlerFactory,
             final int numberOfCrawlers, boolean isBlocking) {
		 System.out.println(" Whole parsing logic is written here to crawl the web");
		 
		 for(int i=1;i<=numberOfCrawlers;i++){
			 T crawler;
			try {
				crawler = crawlerFactory.newInstance();
				Thread thread = new Thread(crawler,"crawler "+i);
				//crawler.setThread(thread);
				crawler.init(i, this);
				thread.start();
			//	cra
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
			 
		 }
		 
		 
	 }
	
	
	
	
	
	
}
