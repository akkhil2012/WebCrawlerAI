package com.cralwer;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class PageFetcher {

	protected CloseableHttpClient httpClient;
	
	protected final CrawlConfig config;
	
	public PageFetcher(){
		this.config = new CrawlConfig();
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		httpClient = clientBuilder.build();
	}
	
	public PageFetcher(CrawlConfig config){
		this.config = config;
		
	}
	
	
	public PageFetchResult fetchPage(WebURL webUrl){
		
		String toFetchURL = webUrl.getURL();
		HttpUriRequest request = null;
				
		PageFetchResult fetchResult = new PageFetchResult();	
		request = newHttpUriRequest(toFetchURL);
		try {
			CloseableHttpResponse response = httpClient.execute(request);
		    /* System.out.println(" ...Transfer response object to Fetchresult Object ...");
		     System.out.println(response.getProtocolVersion());              // HTTP/1.1
             System.out.println(response.getStatusLine().getStatusCode());   // 200
             System.out.println(response.getStatusLine().getReasonPhrase()); // OK
             System.out.println(response.getStatusLine().toString());     */   // HTTP/1.1 200 OK

             HttpEntity entity = response.getEntity();
             if (entity != null) {
                 // return it as a String
            	 System.out.println("Print result Entity........");
                 String result = EntityUtils.toString(entity);
                 //System.out.println(result);
             }
		     fetchResult.setEntity(response.getEntity());
		     fetchResult.setResponseHeaders(response.getAllHeaders());
		     
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" Check response in folder ...");
		return fetchResult;
	}
	
	protected HttpUriRequest newHttpUriRequest(String url) {
        return new HttpGet(url);
    }
}
