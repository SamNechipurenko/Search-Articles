package com.news.generator;

import com.news.generator.service.impl.NewsServiceImpl;
import com.news.generator.model.Article;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GeneratorApplication {

    static NewsServiceImpl newsHandler = new NewsServiceImpl();

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
        List<Article> articleList = newsHandler.getTopArticles(5);
        newsHandler.printTop(articleList, 5);
    }
}
