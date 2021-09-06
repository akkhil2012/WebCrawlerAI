package com.cralwer;

public class CrawlConfig {

	private String crawlStorageFolder;
	
	public String getCrawlStorageFolder() {
		return crawlStorageFolder;
	}

	private int batchReadSize = 50;
	
	public void setCrawlStorageFolder(String crawlStorageFolder) {
        this.crawlStorageFolder = crawlStorageFolder;
    }
	
	public void setBatchReadSize(int batchReadSize) {
        this.batchReadSize = batchReadSize;
    }
	
	public int getBatchReadSize() {
        return batchReadSize;
    }
	
	
}
