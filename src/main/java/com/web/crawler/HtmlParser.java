package com.web.crawler;

public interface HtmlParser {
	  HtmlParseData parse(Page page, String contextURL);
}
