package de.appsfactory.crawlerservice.application.port.in;

import de.appsfactory.crawlerservice.domain.crawler.CrawlingInitQuery;

public interface InitCrawlerUseCase {

    void init(CrawlingInitQuery crawlingInitQuery);

}
