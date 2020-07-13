package de.appsfactory.crawlerservice.application.service;

import de.appsfactory.crawlerservice.application.component.CrawlerComponent;
import de.appsfactory.crawlerservice.domain.crawler.CrawlingInitQuery;
import de.appsfactory.crawlerservice.util.DummyObjects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

public class InitCrawlerServiceTest {

    private final CrawlerComponent crawlerComponent =
            Mockito.mock(CrawlerComponent.class);

    private final InitCrawlerService crawlerService =
            new InitCrawlerService(crawlerComponent);

    @Before
    public void setUp() {
        Assert.assertNotNull("CrawlerComponent is not set", crawlerComponent);
    }

    @Test
    public void whenInitCrawler_thenCrawlerShouldInit() {
        doNothing().when(crawlerComponent).startACrawler(any());
        crawlerService.init(CrawlingInitQuery.from(2, DummyObjects.TEST_PAGE_URL));
    }
}
