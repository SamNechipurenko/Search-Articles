package com.news.generator.client;

import com.news.generator.model.News;
import org.springframework.web.client.RestTemplate;

public class NewsClient implements Client{
    public static final String ARTICLE_BY_COUNTRY_URL =
                            "http://newsapi.org/v2/top-headlines?country=";
    public static final String API_KEY= "fd868cb7d74b41d59cb8f6dc708c521c";
    //RestTemplate thread safe is implemented internally, so we can use static
    public static RestTemplate restTemplate = new RestTemplate();

    @Override
    public News getAllCountryNews(String country){
        // getting all country news;
        String articleLink = NewsClient.ARTICLE_BY_COUNTRY_URL +
                                   country + "&apiKey=" + NewsClient.API_KEY;
        return NewsClient.restTemplate.getForObject(articleLink, News.class);
    }
}
