package com.project.smartcrawler.models;

import java.util.HashMap;

public class PageCrawlDetails {
	String url;
	Integer totalCrawledPages;
	HashMap<String, URLDetails> internalLinks;
	HashMap<String, URLDetails> externalLinks;
	Integer crawlDepth;
	public PageCrawlDetails(String url, HashMap<String, URLDetails> iLinks, HashMap<String, URLDetails> eLinks) {
		this.url = url;
		internalLinks = iLinks;
		externalLinks = eLinks;
		crawlDepth = 0;
	}
	public PageCrawlDetails(String url, HashMap<String, URLDetails> iLinks, HashMap<String, URLDetails> eLinks, int depth) {
		this.url = url;
		internalLinks = iLinks;
		externalLinks = eLinks;
		crawlDepth = depth;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public HashMap<String, URLDetails> getInternalLinks() {
		return internalLinks;
	}
	public void setInternalLinks(HashMap<String, URLDetails> intLinks) {
		this.internalLinks = intLinks;
	}
	public HashMap<String, URLDetails> getExternalLinks() {
		return externalLinks;
	}
	public void setExternalLinks(HashMap<String, URLDetails> extLinks) {
		this.externalLinks = extLinks;
	}
	public Integer getCrawlDepth() {
		return crawlDepth;
	}
	public void setCrawlDepth(Integer crawlDepth) {
		this.crawlDepth = crawlDepth;
	}
	
	public Integer getTotalCrawledPages() {
		return collectedLinkSize();
	}
	public void setTotalCrawledPages(Integer totalCrawledPages) {
		this.totalCrawledPages = collectedLinkSize();
	}
	public Integer collectedLinkSize () {
		return getExternalLinks().size() + getInternalLinks().size();
	}
	
	public void reset() {
		internalLinks.clear();
		externalLinks.clear();
		crawlDepth = 0;
		url = "";
	}

	public boolean isURLProcessed(String url) {
		return (getExternalLinks().get(url) != null) || (getInternalLinks().get(url) != null);
	}
}