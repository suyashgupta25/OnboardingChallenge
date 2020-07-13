package de.appsfactory.crawlerservice.adapter.web;

import de.appsfactory.crawlerservice.adapter.web.response.GetCrawledResultsByNameResponse;
import de.appsfactory.crawlerservice.application.port.in.GetCrawledResultsUseCase;
import de.appsfactory.crawlerservice.application.port.in.InitCrawlerUseCase;
import de.appsfactory.crawlerservice.domain.crawler.WebCrawl;
import de.appsfactory.crawlerservice.util.DummyObjects;
import de.appsfactory.crawlerservice.util.JsonUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WebCrawlerController.class)
public class WebCrawlerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WebCrawlControllerMapper webCrawlControllerMapper;

    @MockBean
    private InitCrawlerUseCase initCrawlerUseCase;

    @MockBean
    private GetCrawledResultsUseCase getCrawledResultsUseCase;

    @Before
    public void setUp() {
        Assert.assertNotNull("InitCrawlerUseCase is not set", initCrawlerUseCase);
        Assert.assertNotNull("GetCrawledResultsUseCase is not set", getCrawledResultsUseCase);
    }

    @Test
    public void whenInitCrawler_returnSuccess() throws Exception {
        doNothing().when(initCrawlerUseCase).init(any());

        mvc.perform(post("/crawler/init")
                .content(JsonUtil.toJson(DummyObjects.getCreateCrawlRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetCrawls_thenReturnJson() throws Exception {
        WebCrawl webCrawl = DummyObjects.getWebCrawlWithId();
        GetCrawledResultsByNameResponse response = DummyObjects.getCrawledResultsByNameResponse();
        given(getCrawledResultsUseCase.getResultsByQuery(any())).willReturn(Arrays.asList(webCrawl));
        given(webCrawlControllerMapper.mapToCrawlResultsByName(any())).willReturn(response);

        mvc.perform(get("/crawler/getNameSortedResults/count/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pageUrl", is(webCrawl.getCrawledUrl().getUrl())));
    }

}
