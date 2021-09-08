package com.web.crawler;

public class Page {

	protected String url;
	protected String contentType;
	public String getContentCharset() {
		return contentCharset;
	}

	public void setContentCharset(String contentCharset) {
		this.contentCharset = contentCharset;
	}

	protected String contentCharset;

	protected byte[] contentData;
	
	public byte[] getContentData() {
		return contentData;
	}

	public void setContentData(byte[] contentData) {
		this.contentData = contentData;
	}

	protected ParseData parseData;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Page(String url) {
		super();
		this.url = url;
	}

	public ParseData getParseData() {
		return parseData;
	}

	public void setParseData(ParseData parseData) {
		this.parseData = parseData;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
