package de.appsfactory.crawlerservice.application.service;

import de.appsfactory.crawlerservice.application.port.in.GetCrawledResultsUseCase;
import de.appsfactory.crawlerservice.application.port.out.LoadWebCrawlsPort;
import de.appsfactory.crawlerservice.domain.crawler.GetCrawlResultsQuery;
import de.appsfactory.crawlerservice.domain.crawler.WebCrawl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
class GetCrawledResultsService implements GetCrawledResultsUseCase {

    private final LoadWebCrawlsPort loadWebCrawlsPort;

    @Override
    public List<WebCrawl> getResultsByQuery(GetCrawlResultsQuery getCrawlResultsQuery) {
        return loadWebCrawlsPort.loadCrawlsSortedByTitle(getCrawlResultsQuery);
    }
}
