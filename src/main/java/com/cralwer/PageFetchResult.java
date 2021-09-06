package com.cralwer;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class PageFetchResult {

	 protected HttpEntity entity = null;
	    protected Header[] responseHeaders = null;
	 public void setEntity(HttpEntity entity) {
	        this.entity = entity;
	    }
	 
	 
	 public HttpEntity getEntity() {
		return entity;
	}


	public void setResponseHeaders(Header[] responseHeaders) {
	        this.responseHeaders = responseHeaders;
	    }
}
