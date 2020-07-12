package de.appsfactory.crawlerservice.adapter.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface WebCrawlRepository extends JpaRepository<WebCrawlJpaEntity, Long>  {

    List<WebCrawlJpaEntity> findByOrderByPageTitleAsc(Pageable pageable);
}
