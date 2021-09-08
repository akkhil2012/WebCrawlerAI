package com.web.crawler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class PageFetcher {
	
	protected CloseableHttpClient httpClient;
	protected CloseableHttpResponse response;
	
	protected final CrawlConfig config;
	
	PageFetcher(CrawlConfig config){
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		httpClient = clientBuilder.build();
		this.config = config;
	}

	private void dumpToSink(String str){
		
		try {
			OutputStream os = new FileOutputStream("C:/sample/text.html");
			try {
				os.write(str.getBytes(Charset.forName("UTF-8")));
				System.out.println(" Written to the otputStream............");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public PageFetchResult fetchPage(String url) {
		PageFetchResult fetchResult = new PageFetchResult();
		HttpUriRequest request = null;
		try {
			request = new HttpGet(url);
			response = httpClient.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int responseCode = response.getStatusLine().getStatusCode();
		//System.out.println(" Status Code is " + responseCode);

		HttpEntity entity = response.getEntity();
		
		String responseString;
		try {
			responseString = EntityUtils.toString(entity, "UTF-8");
			//System.out.println(responseString);

			//dumpToSink(responseString);
			//System.out.println("Fetched Page content is " + responseString);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		fetchResult.setEntity(response.getEntity());

		return fetchResult;
	}

}
