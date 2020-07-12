package de.appsfactory.crawlerservice.adapter.persistence;

import de.appsfactory.crawlerservice.application.port.out.CreateOrUpdateWebCrawlsPort;
import de.appsfactory.crawlerservice.application.port.out.LoadWebCrawlsPort;
import de.appsfactory.crawlerservice.domain.crawler.GetCrawlResultsQuery;
import de.appsfactory.crawlerservice.domain.crawler.WebCrawl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
class WebCrawlsCrawlJpaAdapter implements LoadWebCrawlsPort, CreateOrUpdateWebCrawlsPort {

    private final WebCrawlRepository webCrawlRepository;
    private final WebCrawlPersistenceMapper webCrawlPersistenceMapper;

    @Override
    public WebCrawl save(WebCrawl webCrawl) {
        WebCrawlJpaEntity jpaEntity = webCrawlRepository.save(webCrawlPersistenceMapper.mapToJpaEntity(webCrawl));
        return webCrawlPersistenceMapper.mapToDomainEntity(jpaEntity);
    }

    @Override
    public List<WebCrawl> saveAll(List<WebCrawl> webCrawls) {
        List<WebCrawlJpaEntity> webCrawlJpaEntities = webCrawlRepository.saveAll(webCrawls.stream()
                .map(webCrawlPersistenceMapper::mapToJpaEntity)
                .collect(Collectors.toList()));
        return webCrawlJpaEntities.stream().
                map(webCrawlPersistenceMapper::mapToDomainEntity).
                collect(Collectors.toList());
    }

    @Override
    public List<WebCrawl> loadCrawlsSortedByTitle(GetCrawlResultsQuery getCrawlResultsQuery) {
        List<WebCrawlJpaEntity> byOrderByPageTitleAsc = webCrawlRepository.findByOrderByPageTitleAsc(PageRequest.of(0, getCrawlResultsQuery.getCount()));
        return byOrderByPageTitleAsc.stream().
                map(webCrawlPersistenceMapper::mapToDomainEntity).
                collect(Collectors.toList());
    }
}