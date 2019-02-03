package com.project.smartcrawler.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.smartcrawler.models.CrawlStatus;
import com.project.smartcrawler.models.PageCrawlDetails;
import com.project.smartcrawler.models.URLDetails;

public class SmartCrawlerService implements ICrawlerService  {

	private PageCrawlDetails crawlDetails;
	private String rootHost;
	private String rootUrl;
	private Integer currentDepth = 0;
	private Integer urlThershold;
	private CrawlStatus status;
	
	public SmartCrawlerService(String url, Integer depth) {
		crawlDetails = new PageCrawlDetails(url,
											new HashMap<String, URLDetails>(),
											new HashMap<String, URLDetails>(),
											depth);
		rootUrl = url;
		urlThershold = 100;
		status = CrawlStatus.SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see com.project.smartcrawler.service.ICrawlerService#processPage()
	 */
	@Override
	public PageCrawlDetails processPage() {
		init();
		getPageLinks(rootUrl, currentDepth);
		return crawlDetails;
	}

	/**
	 * This method does based validations and kick starts crawling for the given
	 * URL and depth.
	 * 
	 * @param uri
	 * @param depth
	 */
	public CrawlStatus getPageLinks(String uri, Integer depth) {
		System.out.println("Processing Page for URI: " + uri);
		if (crawlDetails.collectedLinkSize() == urlThershold) {
			status = CrawlStatus.INTERRUPTED_THERSHOLD;
			System.out.println("Processed 100 URLs, so coming out..."); 
		}
		try {
			if (!crawlDetails.isURLProcessed(uri)) {
				processURI(uri, depth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * This method actually does the page processing like:
	 * 	1) Connecting to the Page
	 * 	2) Fetching embedded URLs from the page
	 *  3) Accessibility of all the URLs
	 *  
	 * @param uri
	 * @param depth
	 */
	private void processURI(String uri, Integer depth) {
		URLDetails details = new URLDetails();
		details.setUri(uri);
		URL url = null;
		try {
			url = new URL(uri);
			if (url != null && url.getHost().equals(rootHost)) {
				details.setInternal(true);
				crawlDetails.getInternalLinks().put(uri, details);
			} else {
				crawlDetails.getExternalLinks().put(uri, details);
			}
			details.setProtocol(url.getProtocol());
			details.setUriDepth(depth - 1);
			Response response = Jsoup.connect(uri).timeout(3000).followRedirects(true).execute();
			Document document = response.parse();
			if (!uri.equals(response.url().toString())) {
				details.setRedirected(true);
				details.setRedirectedURI(response.url().toString());
			}
			Elements linksOnPage = document.select("a[href]");
			if (depth <= crawlDetails.getCrawlDepth()) {
				depth++;
				for (Element page : linksOnPage) {
					getPageLinks(page.attr("href"), depth);
				}
			} else {
				System.out.println("Urls processed for given depth!!");
			}
		} catch (IOException e) {
			details.setReachable(false);
			details.setUnreachableReason(e.getMessage());
		}
		return;	
	}

	/**
	 * Initializes / resets all the required parameters for the processing.
	 */
	private void init () {
		try {
			URL url1 = new URL(crawlDetails.getUrl());
			currentDepth = 0;
			rootHost = url1.getHost();
		} catch (MalformedURLException e) {
			System.out.println("Failed to initialize..Error:");
		}
	}

}
