package de.appsfactory.crawlerservice.util;

import de.appsfactory.crawlerservice.adapter.web.request.CreateCrawlerRequest;
import de.appsfactory.crawlerservice.adapter.web.response.GetCrawledResultsByNameResponse;
import de.appsfactory.crawlerservice.domain.crawler.*;

import java.util.Calendar;
import java.util.Date;

public class DummyObjects {
    private static final String TEST_PAGE_NAME = "Europe";
    public static final String TEST_PAGE_URL = "https://en.wikipedia.org/wiki/Europe";

    public static WebCrawl getWebCrawlWithoutId() {
        WebCrawl webCrawl = WebCrawl.withoutId(WebCrawlPageTitle.from(TEST_PAGE_NAME),
                WebCrawlPageUrl.from(TEST_PAGE_URL),
                WebCrawlPageUrl.from(TEST_PAGE_URL),
                WebCrawlPageDepth.from((short)0),
                WebCrawlPageDate.from(new Date()));
        return webCrawl;
    }

    public static WebCrawl getWebCrawlWithId() {
        WebCrawl webCrawl = WebCrawl.withId(new WebCrawl.WebCrawlId(1L),
                WebCrawlPageTitle.from(TEST_PAGE_NAME),
                WebCrawlPageUrl.from(TEST_PAGE_URL),
                WebCrawlPageUrl.from(TEST_PAGE_URL),
                WebCrawlPageDepth.from((short)0),
                WebCrawlPageDate.from(new Date()));
        return webCrawl;
    }

    public static GetCrawledResultsByNameResponse getCrawledResultsByNameResponse() {
        return new GetCrawledResultsByNameResponse(TEST_PAGE_NAME, TEST_PAGE_URL, TEST_PAGE_URL, (short)0, new Date());
    }

    public static CreateCrawlerRequest getCreateCrawlRequest() {
        return new CreateCrawlerRequest(TEST_PAGE_URL, 2);
    }
}
