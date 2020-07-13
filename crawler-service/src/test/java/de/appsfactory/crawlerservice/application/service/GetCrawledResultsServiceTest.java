package de.appsfactory.crawlerservice.application.service;

import de.appsfactory.crawlerservice.application.port.out.LoadWebCrawlsPort;
import de.appsfactory.crawlerservice.domain.crawler.GetCrawlResultsQuery;
import de.appsfactory.crawlerservice.domain.crawler.WebCrawl;
import de.appsfactory.crawlerservice.util.DummyObjects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class GetCrawledResultsServiceTest {

    private final LoadWebCrawlsPort loadWebCrawlsPort =
            Mockito.mock(LoadWebCrawlsPort.class);

    private final GetCrawledResultsService getCrawledResultsService =
            new GetCrawledResultsService(loadWebCrawlsPort);

    @Before
    public void setUp() {
        Assert.assertNotNull("loadWebCrawlsPort is not set", loadWebCrawlsPort);
    }

    @Test
    public void whenGetCrawls_thenCrawlsShouldBeFetched() {
        WebCrawl webCrawlWithoutId = DummyObjects.getWebCrawlWithoutId();
        Mockito.when(loadWebCrawlsPort.loadCrawlsSortedByTitle(any()))
                .thenReturn(Arrays.asList(webCrawlWithoutId));

        List<WebCrawl> webCrawls = getCrawledResultsService.getResultsByQuery(GetCrawlResultsQuery.from(1));
        assertThat(webCrawls).isNotEmpty();
        assertThat(webCrawls.get(0).getCrawledUrl().getUrl()).isEqualTo(DummyObjects.TEST_PAGE_URL);
    }
}
