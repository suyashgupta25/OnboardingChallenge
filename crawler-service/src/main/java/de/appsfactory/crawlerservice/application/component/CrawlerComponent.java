package de.appsfactory.crawlerservice.application.component;

import de.appsfactory.crawlerservice.config.Crawler4jProperties;
import de.appsfactory.crawlerservice.domain.crawler.CrawlingInitQuery;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CrawlerComponent {

    private final Crawler4jProperties properties;
    private final Function<Integer, CrawlConfig> crawlConfigFactory;
    private final Function<CrawlConfig, CrawlController> crawlControllerFactory;
    private final Function<CrawlingInitQuery, DataCrawler> dataCrawlerFactory;
    private CrawlController crawlController;

    public CrawlerComponent(Function<Integer, CrawlConfig> crawlConfigFactory, Function<CrawlConfig,
            CrawlController> crawlControllerFactory, Function<CrawlingInitQuery, DataCrawler> dataCrawlerFactory,
                            Crawler4jProperties properties) {
        this.crawlConfigFactory = crawlConfigFactory;
        this.crawlControllerFactory = crawlControllerFactory;
        this.dataCrawlerFactory = dataCrawlerFactory;
        this.properties = properties;
    }

    public void startACrawler(CrawlingInitQuery crawlingInitQuery) {
        if (crawlController != null && !crawlController.isFinished()) {
            return;
        }
        CrawlConfig crawlConfig = crawlConfigFactory.apply(crawlingInitQuery.getDepth());
        crawlController = crawlControllerFactory.apply(crawlConfig);
        crawlController.addSeed(crawlingInitQuery.getUrl().toString());
        CrawlController.WebCrawlerFactory<DataCrawler> factory = () -> dataCrawlerFactory.apply(crawlingInitQuery);
        crawlController.startNonBlocking(factory, properties.getNumberOfCrawlers());
    }
}
