package de.appsfactory.crawlerservice.application.port.out;


import de.appsfactory.crawlerservice.domain.crawler.WebCrawl;

import java.util.List;

public interface CreateOrUpdateWebCrawlsPort {
    WebCrawl save(WebCrawl webCrawl);

    List<WebCrawl> saveAll(List<WebCrawl> webCrawls);

}
