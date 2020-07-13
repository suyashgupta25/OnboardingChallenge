package de.appsfactory.crawlerservice.application.component;

import de.appsfactory.crawlerservice.application.port.out.CreateOrUpdateWebCrawlsPort;
import de.appsfactory.crawlerservice.domain.crawler.*;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataCrawler extends WebCrawler {

    private final static Pattern EXCLUSIONS = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");
    private final CreateOrUpdateWebCrawlsPort createOrUpdateWebCrawlsPort;
    private final CrawlingInitQuery crawlingInitQuery;

    public DataCrawler(CreateOrUpdateWebCrawlsPort createOrUpdateWebCrawlsPort, CrawlingInitQuery crawlingInitQuery) {
        this.createOrUpdateWebCrawlsPort = createOrUpdateWebCrawlsPort;
        this.crawlingInitQuery = crawlingInitQuery;
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        final String urlString = url.getURL().toLowerCase();
        return !EXCLUSIONS.matcher(urlString).matches()
                && urlString.startsWith(crawlingInitQuery.getBaseUrl());
    }

    @Override
    public void visit(Page page) {
        final String url = page.getWebURL().getURL();
        final short depth = page.getWebURL().getDepth();

        if (page.getParseData() instanceof HtmlParseData) {
            final HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            final String title = htmlParseData.getTitle();

            WebCrawl webCrawl = WebCrawl.withoutId(WebCrawlPageTitle.from(title),
                    WebCrawlPageUrl.from(url),
                    WebCrawlPageUrl.from(crawlingInitQuery.getUrl().toString()),
                    WebCrawlPageDepth.from(depth),
                    WebCrawlPageDate.from(new Date()));
            if (crawlingInitQuery.getDepth() == depth) {
                final Set<WebURL> links = htmlParseData.getOutgoingUrls();
                List<WebCrawl> webCrawlList = links.stream().filter(entry -> (entry.getAnchor() != null && !EXCLUSIONS.matcher(entry.getURL()).matches()))
                        .map(webURL -> WebCrawl.withoutId(WebCrawlPageTitle.from(webURL.getAnchor()),
                                WebCrawlPageUrl.from(webURL.getURL()),
                                WebCrawlPageUrl.from(crawlingInitQuery.getUrl().toString()),
                                WebCrawlPageDepth.from(webURL.getDepth()),
                                WebCrawlPageDate.from(new Date()))).collect(Collectors.toList());
                webCrawlList.add(webCrawl);
                createOrUpdateWebCrawlsPort.saveAll(webCrawlList);
            } else {
                createOrUpdateWebCrawlsPort.save(webCrawl);
            }
        }
    }
}
