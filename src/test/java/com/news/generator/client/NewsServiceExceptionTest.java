package com.news.generator.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@AutoConfigureWebTestClient
@SpringBootTest()
public class NewsServiceExceptionTest {
    public static final String API_KEY= "fd868cb7d74b41d59cb8f6dc708c521c";
    public static Logger logger = LoggerFactory.getLogger(NewsServiceExceptionTest.class);;
    private NewsClient newsClient = new NewsClient();

    //@Autowired
    //WebTestClient client;

    // if API_KEY is invalid check RuntimeException
    @Test()
    void shouldReturnRuntimeExceptionWhenWrongAPI_KEY(){
        logger.info("method shouldReturnBadRequestWhenWrongAPI_KEY() started");
        logger.info("trying get data from: " + NewsClient.ARTICLE_BY_COUNTRY_URL);

        assertThrows(RuntimeException.class,
                () -> newsClient
                        .getAllCountryNews("us","")
        );

//        client.get()
//                .uri(NewsClient.ARTICLE_BY_COUNTRY_URL)
//                .exchange()
//                .expectStatus()
//                .isBadRequest();
    }

    // if country symbol is invalid check RuntimeException
    @Test()
    void shouldReturnRuntimeExceptionWhenWrongCountry(){
        String wrongCountry = "";
        String articleLink = NewsClient.ARTICLE_BY_COUNTRY_URL +
                wrongCountry + "&apiKey=" + API_KEY;
        logger.info("method shouldReturnRuntimeExceptionWhenWrongCountry() started");
        logger.info("trying get data from: " + articleLink);

        assertThrows(RuntimeException.class,
                        () -> newsClient
                            .getAllCountryNews(wrongCountry, API_KEY)
                    );
    }
}
