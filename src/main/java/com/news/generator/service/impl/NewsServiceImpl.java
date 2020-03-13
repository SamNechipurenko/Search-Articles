package com.news.generator.service.impl;

import com.news.generator.service.NewsService;
import com.news.generator.model.Article;
import com.news.generator.model.News;

import com.news.generator.client.NewsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    public  static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
    public static final String API_KEY= "fd868cb7d74b41d59cb8f6dc708c521c";
    private String countryLine = "us ua";
    // array of country symbols
    private String[] countries = countryLine.split(" ", -1);

    @Override
    public List<Article> getTopArticles(int articleNumber) throws RuntimeException{

        List<Article> articles;
        logger.debug("getTopArticles method started");

        News news;
        NewsClient newsClient = new NewsClient();
        List<Article> allArticles = new ArrayList<>();

        try{
            // getting all news for each country
            for (String country : countries){
                news = newsClient.getAllCountryNews(country, API_KEY);

                // checking if country news are available
                if(news.getStatus().equals("ok")){
                    logger.debug("news for country: " + country + " found");
                    articles = news.getArticles();

                    //add articles to list
                    allArticles.addAll(articles);
                    logger.debug("articles for country: " + country + " added successfully");
                }
            }
            // sorting article list by date
            allArticles.sort(Comparator.comparing(Article::getPublishedAt));
            logger.debug("all articles are sorted by date");

        }catch (Exception ex){
            logger.error("exception in News client API: " + ex);
            throw new RuntimeException("service is unavailable");
        }
        // if parameter articleNumber less than available number of articles then return all articles
        if (allArticles.size() < articleNumber) return allArticles;
        // if parameter articleNumber less than 1 then return null
        if (articleNumber < 1) return null;
        // if parameter articleNumber more than available number of articles then return all articles
        return allArticles.subList(allArticles.size() - articleNumber , allArticles.size());
    }

    @Override
    public void printTop(List<Article> articleList, int articleNumber){

        logger.debug("start print top: " + articleNumber + " information");

        System.out.println("\n");
        // print article information
        for (int articleNum = articleNumber-1 ; articleNum >= 0; articleNum--) {
            System.out.println("title: " + articleList.get(articleNum).getTitle());
            System.out.println("author: " + articleList.get(articleNum).getAuthor());
            System.out.println("published at: " + articleList.get(articleNum).getPublishedAt());
            // System.out.println("link: " + articleList.get(articleNum).getUrl());
            System.out.println("\n");
        }

    }
}
