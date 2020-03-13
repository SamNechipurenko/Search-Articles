package com.news.generator.client.impl;

import com.news.generator.client.NewsClient;
import com.news.generator.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class NewsClientImpl implements NewsClient {

    Logger logger = LoggerFactory.getLogger(NewsClientImpl.class);

    public static final String ARTICLE_BY_COUNTRY_URL =
                            "http://newsapi.org/v2/top-headlines?country=";
    public static final String API_KEY= "fd868cb7d74b41d59cb8f6dc708c521c";

    //RestTemplate thread safe is implemented internally, so we can use static
    public static RestTemplate restTemplate = new RestTemplate();

    @Override
    public News getAllCountryNews(String country){

        logger.debug("method getAllCountryNews() for client API started");

        // getting all country news;
        String articleLink = NewsClientImpl.ARTICLE_BY_COUNTRY_URL +
                                   country + "&apiKey=" + NewsClientImpl.API_KEY;
        return NewsClientImpl.restTemplate.getForObject(articleLink, News.class);
    }
}
