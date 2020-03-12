package com.news.generator.client;

import com.news.generator.model.Article;
import com.news.generator.model.News;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class NewsHandler implements Handler{

    private String countryLine = "us ua";
    // array of country symbols
    private String[] countries = countryLine.split(" ", -1);
    private List<Article> articles;

    
    @Override
    public List<Article> getFirstFiveArticles() throws RuntimeException{

        NewsClient newsClient = new NewsClient();
        List<Article> allArticles = new ArrayList<>();

        try{
            // getting all news for each country
            for (String country : countries){
                News news = newsClient.getAllCountryNews(country);

                // checking if country news are available
                if(news.getStatus().equals("ok")){
                    articles = news.getArticles();

                    //add articles to list
                    allArticles.addAll(articles);
                }
            }
            // sorting article list by date
            allArticles.sort(Comparator.comparing(Article::getPublishedAt));

        }catch (Exception ex){ System.out.println("Exception: " + ex); }

        if (allArticles.isEmpty()) throw new RuntimeException("service is unavailable");

        return allArticles.subList(allArticles.size() - 5 , allArticles.size());
    }

    @Override
    public void printTopFive(List<Article> articleList){

        System.out.println("\n");
        // print article information
        for (int articleNum = 4; articleNum >= 0; articleNum--) {
            System.out.println("title: " + articleList.get(articleNum).getTitle());
            System.out.println("author: " + articleList.get(articleNum).getAuthor());
            System.out.println("published at: " + articleList.get(articleNum).getPublishedAt());
            System.out.println("link: " + articleList.get(articleNum).getUrl());
            System.out.println("\n");
        }

    }
}
