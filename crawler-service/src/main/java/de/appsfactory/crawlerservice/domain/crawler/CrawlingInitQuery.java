package de.appsfactory.crawlerservice.domain.crawler;

import de.appsfactory.crawlerservice.error.exception.InvalidParameterException;
import lombok.*;
import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;
import java.net.URL;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CrawlingInitQuery {

    @NonNull
    @Getter
    private final Integer depth;

    @NonNull
    @Getter
    private final URL url;

    @NonNull
    @Getter
    private final String baseUrl;

    public static CrawlingInitQuery from(@NonNull Integer depth, @NonNull String url) {
        if (isAValidLink(url)) {
            try {
                URL urlValue = new URL(url);
                String baseUrl = urlValue.getProtocol() + "://" + urlValue.getHost();
                return new CrawlingInitQuery(depth, new URL(url), baseUrl);
            } catch (MalformedURLException e) {
                throw new InvalidParameterException(CrawlingInitQuery.class, "url", url);
            }
        } else {
            throw new InvalidParameterException(CrawlingInitQuery.class, "url", url);
        }
    }

    private static boolean isAValidLink(@NonNull String location) {
        return !location.isBlank() && UrlValidator.getInstance().isValid(location);
    }
}
