package de.appsfactory.crawlerservice.application.port.out;

import de.appsfactory.crawlerservice.domain.crawler.GetCrawlResultsQuery;
import de.appsfactory.crawlerservice.domain.crawler.WebCrawl;

import java.util.List;

public interface LoadWebCrawlsPort {
    List<WebCrawl> loadCrawlsSortedByTitle(GetCrawlResultsQuery getCrawlResultsQuery);
}
