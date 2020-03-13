package com.news.generator.client;

import com.news.generator.client.impl.NewsClientImpl;
import com.news.generator.client.impl.NewsHandlerImpl;
import com.news.generator.model.Article;
import com.news.generator.model.News;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class NewsHandlerTest {

    @Autowired
    private NewsHandlerImpl newsHandler;

    @MockBean
    private NewsClientImpl newsClient;

    @Test
    void shouldReturnAllNewsObjectsFromMockedExternalAPI() {

        List<Article> articles = new ArrayList<>();
        News news = new News();

        Article article = new Article();
        article.setTitle("Stock futures down after 11-year bull market run ended Thursday - CNN");
        article.setAuthor("Clare Duffy, CNN Business");
        article.setPublishedAt(new Date());
        articles.add(article);

        article = new Article();
        article.setTitle("Head Of U.S. Soccer Resigns After Equal Pay Backlash - HuffPost");
        article.setAuthor("Nick Visser");
        article.setPublishedAt(new Date());
        articles.add(article);

        news.setArticles(articles);

        Mockito.when(newsClient.getAllCountryNews("us")).thenReturn(news);
        assertEquals(news, newsClient.getAllCountryNews("us"));

    }

}