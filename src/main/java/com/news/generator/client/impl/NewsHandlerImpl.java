package com.news.generator.client.impl;

import com.news.generator.client.NewsHandler;
import com.news.generator.model.Article;
import com.news.generator.model.News;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Component
public class NewsHandlerImpl implements NewsHandler {

    public  static Logger logger = LoggerFactory.getLogger(NewsHandlerImpl.class);

    private String countryLine = "us ua";
    // array of country symbols
    private String[] countries = countryLine.split(" ", -1);
    private List<Article> articles;

    @Override
    public List<Article> getTopArticles(int articleNumber) throws RuntimeException{

        logger.debug("getTopArticles method started");

        NewsClientImpl newsClient = new NewsClientImpl();
        List<Article> allArticles = new ArrayList<>();

        try{
            // getting all news for each country
            for (String country : countries){
                News news = newsClient.getAllCountryNews(country);

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
            System.out.println("link: " + articleList.get(articleNum).getUrl());
            System.out.println("\n");
        }

    }
}
