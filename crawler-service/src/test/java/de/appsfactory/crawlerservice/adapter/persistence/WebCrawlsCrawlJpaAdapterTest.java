package de.appsfactory.crawlerservice.adapter.persistence;

import de.appsfactory.crawlerservice.domain.crawler.GetCrawlResultsQuery;
import de.appsfactory.crawlerservice.domain.crawler.WebCrawl;
import de.appsfactory.crawlerservice.util.DummyObjects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({WebCrawlsCrawlJpaAdapter.class, WebCrawlPersistenceMapper.class})
public class WebCrawlsCrawlJpaAdapterTest {

    @Autowired
    private WebCrawlsCrawlJpaAdapter adapterUnderTest;

    @Test
    @Sql(scripts={"WebCrawlPersistenceAdapterTest.sql"})
    void whenLoadingCrawls_crawlsAreFetchedFromDB() {
        List<WebCrawl> webCrawls = adapterUnderTest.loadCrawlsSortedByTitle(GetCrawlResultsQuery.from(100));
        assertThat(webCrawls).isNotEmpty();
        assertThat(webCrawls.get(0).getCrawledUrl().getUrl()).isEqualTo(DummyObjects.TEST_PAGE_URL);
    }

    @Test
    void whenCreatingACrawl_crawlIsCreatedInDB() {
        WebCrawl crawl = adapterUnderTest.save(DummyObjects.getWebCrawlWithoutId());
        assertThat(crawl.getCrawledUrl().getUrl()).isEqualTo(DummyObjects.TEST_PAGE_URL);
    }

    @Test
    void whenCreatingAListOsCrawls_crawlsAreCreatedInDB() {
        List<WebCrawl> webCrawls = adapterUnderTest.saveAll(Arrays.asList(DummyObjects.getWebCrawlWithoutId()));
        assertThat(webCrawls).isNotEmpty();
        assertThat(webCrawls.get(0).getCrawledUrl().getUrl()).isEqualTo(DummyObjects.TEST_PAGE_URL);
    }
}
