package com.web.crawler;

import java.util.Set;

public interface ParseData {
	Set<WebURL> getOutgoingUrls();
	
	void setOutgoingUrls(Set<WebURL> outgoingUrls);
	
	
}
