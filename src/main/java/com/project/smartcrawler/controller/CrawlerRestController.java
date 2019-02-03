package com.project.smartcrawler.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.smartcrawler.models.PageCrawlDetails;
import com.project.smartcrawler.service.ICrawlerService;
import com.project.smartcrawler.service.SmartCrawlerService;

@RestController
public class CrawlerRestController {
	
	ICrawlerService service;

	/**
	 * This API crawls the given URL.
	 * Based on the value of the depth, crawling depth is decided.
	 * If depth is 0:
	 * 	 Only the given URI Page is crawled and HREFs are returned.
	 * If depth is 1:
	 * 	 HREFs from the first page is also processed.
	 * And goes on.
	 * 
	 * @param url
	 * @param depth
	 * @return JSON Object of type PageCrawlDetails
	 * Refer to: /resources/SampleInputAndOutput.txt for sample output
	 */
	@RequestMapping("/crawl")
	public PageCrawlDetails index(@RequestParam(value="uri", required = true) String url,
								  @RequestParam(value="depth",required = false) Integer depth) {
		service = new SmartCrawlerService(url, depth == null ? 0 : depth);
		return service.processPage();
	}
}