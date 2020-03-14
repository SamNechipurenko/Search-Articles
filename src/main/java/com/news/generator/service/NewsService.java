package com.news.generator.service;

import com.news.generator.model.Article;
import java.util.List;

public interface NewsService {

    String API_KEY = "fd868cb7d74b41d59cb8f6dc708c521c";

    void printTop(List<Article> articleList);
    List<Article> getTopArticles(int articleNumber);
}
