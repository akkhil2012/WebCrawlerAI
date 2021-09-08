package com.web.crawler;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.DublinCore;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.xml.sax.SAXException;

public class TikaHtmlParser implements com.web.crawler.HtmlParser{
	
	
	private final HtmlParser htmlParser;
	private final CrawlConfig config;
	private final TLDList tldList;
	
	private final ParseContext parseContext;
	public TikaHtmlParser(CrawlConfig config,TLDList tldList){
		this.config = config;
		this.tldList = tldList;
		
		htmlParser = new HtmlParser();
		this.parseContext = new ParseContext();
	}
	
	 private String chooseEncoding(Page page, Metadata metadata) {
	        String pageCharset = page.getContentCharset();
	        if (pageCharset == null || pageCharset.isEmpty()) {
	            return metadata.get("Content-Encoding");
	        }
	        return pageCharset;
	    }

	@Override
	public HtmlParseData parse(Page page, String contextURL) {
		HtmlParseData parsedData = new HtmlParseData();
		
		HtmlContentHandler contentHandler = new HtmlContentHandler();
		Metadata metadata = new Metadata();
		
		InputStream inputStream =
				  new ByteArrayInputStream(page.getContentData());
		try {
			htmlParser.parse(inputStream, contentHandler,metadata,parseContext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String contentCharset = chooseEncoding(page, metadata);
        parsedData.setContentCharset(contentCharset);

        parsedData.setText(contentHandler.getBodyText().trim());
        parsedData.setTitle(metadata.get(DublinCore.TITLE));
        parsedData.setMetaTags(contentHandler.getMetaTags());
		
		/*
		 * YET TO PARSE THE OUTGOING URLs
		 */
		
		
		
		return parsedData;
	}

}
