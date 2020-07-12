package de.appsfactory.crawlerservice.application.port.in;

import de.appsfactory.crawlerservice.domain.crawler.GetCrawlResultsQuery;
import de.appsfactory.crawlerservice.domain.crawler.WebCrawl;

import java.util.List;

public interface GetCrawledResultsUseCase {

    List<WebCrawl> getResultsByQuery(GetCrawlResultsQuery getCrawlResultsQuery);

}
