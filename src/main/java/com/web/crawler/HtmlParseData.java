package com.web.crawler;

import java.util.Map;
import java.util.Set;

public class HtmlParseData implements ParseData{
	
	private String html;
	private String text;
	private String title;
	private String contentCharset;
	 private Map<String, String> metaTags;

	@Override
	public Set<WebURL> getOutgoingUrls() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentCharset() {
		return contentCharset;
	}

	public void setContentCharset(String contentCharset) {
		this.contentCharset = contentCharset;
	}

	public Map<String, String> getMetaTags() {
		return metaTags;
	}

	public void setMetaTags(Map<String, String> metaTags) {
		this.metaTags = metaTags;
	}

	@Override
	public void setOutgoingUrls(Set<WebURL> outgoingUrls) {
		// TODO Auto-generated method stub
		
	}

}
