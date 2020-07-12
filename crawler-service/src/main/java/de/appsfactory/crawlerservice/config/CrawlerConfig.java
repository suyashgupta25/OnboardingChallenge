package de.appsfactory.crawlerservice.config;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
        return arg -> provideCrawlConfig(arg);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CrawlConfig provideCrawlConfig(Integer depth) {
        File crawlStorage = new File(properties.getIntermediatesPath());
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());
        config.setMaxDepthOfCrawling(depth);
        return config;
    }

    @Bean
    public Function<CrawlConfig, CrawlController> crawlControllerFactory() {
        return arg -> provideCrawlController(arg);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CrawlController provideCrawlController(CrawlConfig config) {
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controller;
    }

}
