package com.news.generator.client;

import com.news.generator.model.Article;

import java.util.List;

public interface NewsHandler {
    void printTop(List<Article> articleList, int articleNumber);
    List<Article> getTopArticles(int articleNumber);
}
