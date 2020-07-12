package de.appsfactory.crawlerservice.domain.crawler;

import de.appsfactory.crawlerservice.error.exception.InvalidParameterException;
import lombok.*;
import org.apache.commons.validator.routines.UrlValidator;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WebCrawlPageUrl {

    @NonNull
    @Getter
    private final String url;

    public static WebCrawlPageUrl from(@NonNull String url) {
        if (isAValidLink(url)) {
            return new WebCrawlPageUrl(url);
        } else {
            throw new InvalidParameterException(WebCrawlPageUrl.class, "url", url);
        }
    }

    private static boolean isAValidLink(@NonNull String url) {
        return !url.isEmpty() && UrlValidator.getInstance().isValid(url);
    }
}
