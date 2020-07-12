package de.appsfactory.crawlerservice.config;

import de.appsfactory.crawlerservice.util.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "crawler4j")
@PropertySource(value = "classpath:crawler4j.yml", factory = YamlPropertySourceFactory.class)
@Data
public class Crawler4jProperties {

    private String intermediatesPath;

    private int numberOfCrawlers;

}
