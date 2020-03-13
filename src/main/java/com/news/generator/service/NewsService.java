package com.news.generator.service;

import com.news.generator.model.Article;

import java.util.List;

public interface NewsService {
    void printTop(List<Article> articleList, int articleNumber);
    List<Article> getTopArticles(int articleNumber);
}
