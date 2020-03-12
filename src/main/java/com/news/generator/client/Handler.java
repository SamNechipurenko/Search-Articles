package com.news.generator.client;

import com.news.generator.model.Article;

import java.util.List;

public interface Handler {
    void printTopFive(List<Article> articleList);
    List<Article> getFirstFiveArticles();
}
