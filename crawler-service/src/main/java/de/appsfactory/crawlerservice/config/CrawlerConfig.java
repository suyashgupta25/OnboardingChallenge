package de.appsfactory.crawlerservice.config;

import de.appsfactory.crawlerservice.application.component.DataCrawler;
import de.appsfactory.crawlerservice.application.port.out.CreateOrUpdateWebCrawlsPort;
import de.appsfactory.crawlerservice.domain.crawler.CrawlingInitQuery;
import de.appsfactory.crawlerservice.error.exception.ComponentInitializationException;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.function.Function;

@Configuration
public class CrawlerConfig {

    private final Crawler4jProperties properties;

    public CrawlerConfig(Crawler4jProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Function<Integer, CrawlConfig> crawlConfigFactory() {
        return arg -> {
            File crawlStorage = new File(properties.getIntermediatesPath());
            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());
            config.setMaxDepthOfCrawling(arg);
            return config;
        };
    }

    @Bean
    public Function<CrawlConfig, CrawlController> crawlControllerFactory() {
        return arg -> {
            PageFetcher pageFetcher = new PageFetcher(arg);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
            CrawlController controller = null;
            try {
                controller = new CrawlController(arg, pageFetcher, robotstxtServer);
            } catch (Exception e) {
                throw new ComponentInitializationException(CrawlController.class, "reason", e.getLocalizedMessage());
            }
            return controller;
        };
    }

    @Bean
    public Function<CrawlingInitQuery, DataCrawler> dataCrawlerFactory(CreateOrUpdateWebCrawlsPort createOrUpdateWebCrawlsPort) {
        return arg -> new DataCrawler(createOrUpdateWebCrawlsPort, arg);
    }
}
