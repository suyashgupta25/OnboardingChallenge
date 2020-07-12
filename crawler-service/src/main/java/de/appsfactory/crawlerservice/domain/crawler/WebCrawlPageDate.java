package de.appsfactory.crawlerservice.domain.crawler;

import lombok.*;

import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WebCrawlPageDate {

    @NonNull
    @Getter
    @PastOrPresent
    private final Date crawledAt;

    public static WebCrawlPageDate from(@NonNull Date crawledAt) {
        return new WebCrawlPageDate(crawledAt);
    }
}
