package de.appsfactory.crawlerservice.adapter.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WEB_CRAWLS")
@Data
@AllArgsConstructor
@NoArgsConstructor
class WebCrawlJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String pageTitle;

    @NonNull
    private String crawledPageUrl;

    @NonNull
    private Short depth;

    @NonNull
    private String seed;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date crawledAt;
}
