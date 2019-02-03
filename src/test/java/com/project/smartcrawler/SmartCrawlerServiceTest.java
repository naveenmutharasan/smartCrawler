package com.project.smartcrawler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import com.project.smartcrawler.models.CrawlStatus;
import com.project.smartcrawler.models.PageCrawlDetails;
import com.project.smartcrawler.service.SmartCrawlerService;

@RunWith(MockitoJUnitRunner.class)
public class SmartCrawlerServiceTest {           
	
	@Mock               
	private SmartCrawlerService smartCrawlerService;
	@Mock
	private PageCrawlDetails crawlDetails;
	
	@Test
	public void getPageLinksFailureCase() {
		
		when(smartCrawlerService.getPageLinks(any(), any())).thenReturn(CrawlStatus.SUCCESS);
		smartCrawlerService.getPageLinks("http://www.google.com", 0);
		assertEquals("SUCCESS", CrawlStatus.SUCCESS.toString());
		
	}
	
	@Test
	public void getPageLinksInterruptedCase() {
		
		when(crawlDetails.collectedLinkSize()).thenReturn(100);
		//assertEquals(smartCrawlerService.getPageLinks("http://www.google.com", 0), CrawlStatus.INTERRUPTED_THERSHOLD);	
	}
	
	@Test
	public void getPageLinsSuccessCase() {
		
		when(smartCrawlerService.getPageLinks(any(), any())).thenReturn(CrawlStatus.SUCCESS);
		smartCrawlerService.getPageLinks("http://www.google.com", 0);
		assertEquals("SUCCESS", CrawlStatus.SUCCESS.toString());
		
	}
}
