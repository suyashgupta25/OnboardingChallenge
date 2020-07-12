package de.appsfactory.crawlerservice.adapter.web;

import de.appsfactory.crawlerservice.adapter.web.request.CreateCrawlerRequest;
import de.appsfactory.crawlerservice.adapter.web.response.GetCrawledResultsByNameResponse;
import de.appsfactory.crawlerservice.application.port.in.GetCrawledResultsUseCase;
import de.appsfactory.crawlerservice.application.port.in.InitCrawlerUseCase;
import de.appsfactory.crawlerservice.domain.crawler.CrawlingInitQuery;
import de.appsfactory.crawlerservice.domain.crawler.GetCrawlResultsQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CrawlerController {

    private final InitCrawlerUseCase initCrawlerUseCase;
    private final GetCrawledResultsUseCase getCrawledResultsUseCase;
    private final WebCrawlControllerMapper webCrawlControllerMapper;

    @PostMapping("/crawler/init")
    @ResponseStatus(HttpStatus.OK)
    public void initUrlCrawler(@RequestBody @Valid final CreateCrawlerRequest request) throws Exception {
        log.debug("stating the crawler");
        initCrawlerUseCase.init(CrawlingInitQuery.from(request.getDepth(), request.getUrl()));
    }

    @RequestMapping(value = "/crawler/getNameSortedResults/count/{count}", method = RequestMethod.GET)
    public ResponseEntity<List<GetCrawledResultsByNameResponse>> getCrawlerResultsSortedByName(@PathVariable @Valid Integer count) throws Exception {
        log.debug("fetching sorted content based on count");
        var resultsByQuery = getCrawledResultsUseCase.getResultsByQuery(GetCrawlResultsQuery.from(count));
        List<GetCrawledResultsByNameResponse> responseEntity = resultsByQuery.stream().
                map(webCrawlControllerMapper::mapToCrawlResultsByName).collect(Collectors.toList());
        return ResponseEntity.ok(responseEntity);
    }
}
