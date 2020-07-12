package de.appsfactory.crawlerservice.adapter.web.request;

import de.appsfactory.crawlerservice.common.SelfValidating;
import lombok.Data;
import lombok.NonNull;

@Data
public class CreateCrawlerRequest extends SelfValidating<CreateCrawlerRequest> {
    @NonNull
    private String url;

    @NonNull
    private Integer depth;
}
