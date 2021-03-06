package com.news.generator.client;

import com.news.generator.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class NewsClient {

    public static Logger logger = LoggerFactory.getLogger(NewsClient.class);

    public static final String ARTICLE_BY_COUNTRY_URL =
                            "http://newsapi.org/v2/top-headlines?country=";

    //RestTemplate thread safe is implemented internally, so we can use static
    public static RestTemplate restTemplate = new RestTemplate();

    public News getAllCountryNews(String country, String API_KEY){
        //API_KEY= "fd868cb7d74b41d59cb8f6dc708c521c"
        logger.debug("method getAllCountryNews() for client API started");

        // getting all country news;
        String articleLink = NewsClient.ARTICLE_BY_COUNTRY_URL +
                                   country + "&apiKey=" + API_KEY;
        logger.debug("getting news from: " + articleLink);
        return NewsClient.restTemplate.getForObject(articleLink, News.class);
    }
}
