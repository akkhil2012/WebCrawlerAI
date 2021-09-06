package com.cralwer;

public class Frontier {

	 protected final Object mutex = new Object();
	 protected WorkQueues workQueues;
	 protected long scheduledPages;
	 
	 public Frontier(){
		 workQueues = new WorkQueues();
	 }
	 
	 public void schedule(WebURL url) {
		 int maxPagesToFetch = 10;
		 synchronized (mutex) {
			 if(scheduledPages < maxPagesToFetch){
				 workQueues.put(url);
				 System.out.println(" Processing Page");
				 scheduledPages++;
			 }
			 
		}
		 
	 }
}
