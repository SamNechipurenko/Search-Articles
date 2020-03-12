package com.news.generator.controller;

import com.news.generator.client.NewsHandler;
import com.news.generator.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsHandler newsHandler;

    @GetMapping("/news")
    public List<Article> showAllNews(){
        return newsHandler.getFirstFiveArticles();
    }
}
