package de.appsfactory.crawlerservice.domain.crawler;

import lombok.*;

import javax.validation.constraints.Positive;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCrawlResultsQuery {

    @NonNull
    @Getter
    @Positive
    private final Integer count;

    public static GetCrawlResultsQuery from(@NonNull Integer count) {
        return new GetCrawlResultsQuery(count);
    }
}

