package com.news.generator;

import com.news.generator.service.impl.NewsServiceImpl;
import com.news.generator.model.Article;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GeneratorApplication {

    static NewsServiceImpl newsService = new NewsServiceImpl();

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
        // get top 5 articles
        List<Article> articleList = newsService.getTopArticles(5);
        // print top 5 articles
        newsService.printTop(articleList);
    }
}
