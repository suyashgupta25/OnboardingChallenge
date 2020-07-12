package de.appsfactory.crawlerservice.adapter.persistence;

import de.appsfactory.crawlerservice.domain.crawler.*;
import org.springframework.stereotype.Component;

@Component
class WebCrawlPersistenceMapper {

    WebCrawl mapToDomainEntity(WebCrawlJpaEntity entity) {
        return WebCrawl.withId(new WebCrawl.WebCrawlId(entity.getId()),
                WebCrawlPageTitle.from(entity.getPageTitle()),
                WebCrawlPageUrl.from(entity.getCrawledPageUrl()),
                WebCrawlPageUrl.from(entity.getSeed()),
                WebCrawlPageDepth.from(entity.getDepth()),
                WebCrawlPageDate.from(entity.getCrawledAt()));
    }

    WebCrawlJpaEntity mapToJpaEntity(WebCrawl webCrawl) {
        Long id = webCrawl.getId().map(WebCrawl.WebCrawlId::getValue).orElse(null);
        return new WebCrawlJpaEntity(id,
                webCrawl.getPageTitle().getTitle(), webCrawl.getCrawledUrl().getUrl(),
                webCrawl.getPageDepth().getDepth(), webCrawl.getSeed().getUrl(),
                webCrawl.getCrawledAt().getCrawledAt());
    }
}
