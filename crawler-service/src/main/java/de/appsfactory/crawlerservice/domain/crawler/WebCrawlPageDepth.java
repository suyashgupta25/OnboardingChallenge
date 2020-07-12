package de.appsfactory.crawlerservice.domain.crawler;

import lombok.*;

import javax.validation.constraints.PositiveOrZero;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WebCrawlPageDepth {

    @NonNull
    @Getter
    @PositiveOrZero
    private final Short depth;

    public static WebCrawlPageDepth from(@NonNull Short depth) {
        return new WebCrawlPageDepth(depth);
    }
}
