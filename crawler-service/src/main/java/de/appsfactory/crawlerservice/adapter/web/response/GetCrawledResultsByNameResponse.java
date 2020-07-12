package de.appsfactory.crawlerservice.adapter.web.response;

import de.appsfactory.crawlerservice.common.SelfValidating;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class GetCrawledResultsByNameResponse extends SelfValidating<GetCrawledResultsByNameResponse> {

    @NonNull
    private String pageTitle;

    @NonNull
    private String pageUrl;

    @NonNull
    private String parentUrl;

    @NonNull
    private Short depth;

    @NonNull
    private Date crawledAt;
}
