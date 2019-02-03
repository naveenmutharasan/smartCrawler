package com.project.smartcrawler.service;

import com.project.smartcrawler.models.PageCrawlDetails;

public interface ICrawlerService {

	/**
	 * The Page loaded from the given URL will be analyzed/crawled for the
	 * configured depth.
	 * 
	 * @return
	 */
	public PageCrawlDetails processPage();
}
