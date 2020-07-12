package de.appsfactory.crawlerservice.domain.crawler;

import de.appsfactory.crawlerservice.error.exception.InvalidParameterException;
import lombok.*;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WebCrawlPageTitle {

    @NonNull
    @Getter
    private final String title;

    public static WebCrawlPageTitle from(@NonNull String title) {
        if (isAValidName(title)) {
            return new WebCrawlPageTitle(title);
        } else {
            throw new InvalidParameterException(WebCrawlPageTitle.class, "title", title);
        }
    }

    private static boolean isAValidName(@NonNull String title) {
        return !title.isBlank();
    }

}
