package de.appsfactory.crawlerservice.domain.crawler;

import lombok.*;

import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WebCrawl {

    private final WebCrawlId id;

    @NonNull
    @Getter
    private final WebCrawlPageTitle pageTitle;

    @NonNull
    @Getter
    private final WebCrawlPageUrl crawledUrl;

    @NonNull
    @Getter
    private final WebCrawlPageUrl seed;

    @NonNull
    @Getter
    private final WebCrawlPageDepth pageDepth;

    @NonNull
    @Getter
    private final WebCrawlPageDate crawledAt;

    public static WebCrawl withoutId(@NonNull WebCrawlPageTitle title, @NonNull WebCrawlPageUrl url, @NonNull WebCrawlPageUrl seed,
                                     @NonNull WebCrawlPageDepth depth, @NonNull WebCrawlPageDate crawledAt) {
        return new WebCrawl(null, title, url, seed, depth, crawledAt);
    }

    public static WebCrawl withId(@NonNull WebCrawlId id, @NonNull WebCrawlPageTitle title, @NonNull WebCrawlPageUrl url,
                                  @NonNull WebCrawlPageUrl seed, @NonNull WebCrawlPageDepth depth, @NonNull WebCrawlPageDate crawledAt) {
        return new WebCrawl(id, title, url, seed, depth, crawledAt);
    }

    public Optional<WebCrawlId> getId(){
        return Optional.ofNullable(this.id);
    }

    @Value
    public static class WebCrawlId {
        private Long value;
    }
}
