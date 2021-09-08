package com.web.crawler;

public class Parser {

	
	private final HtmlParser htmlContentParser;
	private final CrawlConfig config;
	private final Net net;
	
	public Parser(CrawlConfig config) throws IllegalAccessException, InstantiationException{
		this(config, new TikaHtmlParser(config, null));
	}
	
	public Parser(CrawlConfig config,HtmlParser htmlParser) throws IllegalAccessException, InstantiationException {
        this(config, htmlParser, null);
    }
	
	
	
	public Parser(CrawlConfig config, HtmlParser htmlParser, TLDList tldList) {
        this.config = config;
        this.htmlContentParser = htmlParser;
        this.net = new Net(config, tldList);
    }

	// Parses the page contents
	public void parse(Page page, String contextURL) {
		// For now only HTML content is addressed....
		// Need to add other content types are we go ahead
		
		//isHTML
		HtmlParseData parseData = this.htmlContentParser.parse(page,contextURL);
		
		
	}

}
