package de.appsfactory.crawlerservice.adapter.web;

import de.appsfactory.crawlerservice.adapter.web.response.GetCrawledResultsByNameResponse;
import de.appsfactory.crawlerservice.domain.crawler.WebCrawl;
import org.springframework.stereotype.Component;

@Component
class WebCrawlControllerMapper {

    GetCrawledResultsByNameResponse mapToCrawlResultsByName(WebCrawl webCrawl) {
        return new GetCrawledResultsByNameResponse(webCrawl.getPageTitle().getTitle(), webCrawl.getCrawledUrl().getUrl(),
                webCrawl.getSeed().getUrl(), webCrawl.getPageDepth().getDepth(), webCrawl.getCrawledAt().getCrawledAt());
    }

}
