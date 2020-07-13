package de.appsfactory.crawlerservice.integration;

import de.appsfactory.crawlerservice.CrawlerServiceApplication;
import de.appsfactory.crawlerservice.adapter.web.request.CreateCrawlerRequest;
import de.appsfactory.crawlerservice.util.DummyObjects;
import de.appsfactory.crawlerservice.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CrawlerServiceApplication.class)
@AutoConfigureMockMvc
public class WebCrawlerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Sql(scripts={"/de/appsfactory/crawlerservice/adapter/persistence/WebCrawlPersistenceAdapterTest.sql"})
    public void whenGetCrawls_thenStatus200() throws Exception {
        mvc.perform(get("/crawler/getNameSortedResults/count/100").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].pageUrl", is(DummyObjects.TEST_PAGE_URL)));
    }

    @Test
    public void whenInitCrawler_thenStatus200() throws Exception {
        CreateCrawlerRequest createCrawlRequest = DummyObjects.getCreateCrawlRequest();
        mvc.perform(post("/crawler/init").content(JsonUtil.toJson(createCrawlRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
