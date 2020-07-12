package de.appsfactory.crawlerservice.application.service;

import de.appsfactory.crawlerservice.application.component.CrawlerComponent;
import de.appsfactory.crawlerservice.application.port.in.InitCrawlerUseCase;
import de.appsfactory.crawlerservice.domain.crawler.CrawlingInitQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
class InitCrawlerService implements InitCrawlerUseCase {

    private final CrawlerComponent crawlerComponent;

    @Override
    public void init(CrawlingInitQuery crawlingInitQuery) {
        crawlerComponent.startACrawler(crawlingInitQuery);
    }

}
