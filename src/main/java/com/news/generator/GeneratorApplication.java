package com.news.generator;

import com.news.generator.client.NewsHandler;
import com.news.generator.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GeneratorApplication {

    static NewsHandler newsHandler = new NewsHandler();

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
        List<Article> articleList = newsHandler.getFirstFiveArticles();
        newsHandler.printTopFive(articleList);

    }

}
