package com.news.generator.client;

import com.news.generator.service.NewsService;
import com.news.generator.service.impl.NewsServiceImpl;
import com.news.generator.model.Article;
import com.news.generator.model.News;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class NewsServiceTest {
    public static Logger logger = LoggerFactory.getLogger(NewsServiceTest.class);
    @MockBean
    private NewsServiceImpl newsHandler;

    @MockBean
    private NewsClient newsClient;

    // Testing method NewsClient.getAllCountryNews()
    @Test
    void shouldReturnAllNewsObjectsFromMockedExternalAPI() {

        logger.info("start testing NewsClient.getAllCountryNews()");
        logger.info("start adding articles");
        List<Article> articles = new ArrayList<>();
        News news = new News();

        Article article = new Article();
        article.setTitle("Stock futures down after 11-year bull market run ended Thursday - CNN");
        article.setAuthor("Clare Duffy, CNN Business");
        article.setPublishedAt(new Date(1584080018121L));
        articles.add(article);

        article = new Article();
        article.setTitle("Head Of U.S. Soccer Resigns After Equal Pay Backlash - HuffPost");
        article.setAuthor("Nick Visser");
        article.setPublishedAt(new Date(1584080118121L));
        articles.add(article);

        news.setStatus("ok");
        news.setArticles(articles);
        logger.info("articles are added");

        Mockito.when(newsClient.getAllCountryNews("us", NewsService.API_KEY)).thenReturn(news);
        assertEquals(news, newsClient.getAllCountryNews("us", NewsService.API_KEY));

    }

    // Testing method NewsHandlerImpl.getTopArticles()
    @Test
    void shouldReturnTopFive() {
        logger.info("start testing NewsHandlerImpl.getTopArticles()");
        List<Article> articles = new ArrayList<>();
        logger.info("adding six articles");

        Article article = new Article();
        article.setTitle("Stock futures down after 11-year bull market run ended Thursday - CNN");
        article.setAuthor("Clare Duffy, CNN Business");
        article.setPublishedAt(new Date(1584080010921L));
        articles.add(article);

        article = new Article();
        article.setTitle("'The Bachelor': Hannah Ann Sluss" +
                " and Peter Weber's Breakup Timeline Fills in Every Red Flag - Showbiz Cheat Sheet");
        article.setAuthor("Nick Visser");
        article.setPublishedAt(new Date(1584080015121L));
        articles.add(article);

        article = new Article();
        article.setTitle("Stock futures down after 11-year bull market run ended Thursday - CNN");
        article.setAuthor("Kate Conger, David E. Sanger");
        article.setPublishedAt(new Date(1584080048121L));
        articles.add(article);

        article = new Article();
        article.setTitle("Head Of U.S. Soccer Resigns After Equal Pay Backlash - HuffPost");
        article.setAuthor("Connie Liou");
        article.setPublishedAt(new Date(1584080019121L));
        articles.add(article);

        article = new Article();
        article.setTitle("Вольфсбург – Шахтар: Каштру назвав основну перевагу" +
                " \"гірників\" та пояснив, чому довірив пенальті Коваленку — Футбол 24 - Футбол 24");
        article.setAuthor("football24.ua");
        article.setPublishedAt(new Date(1584080018121L));
        articles.add(article);

        article = new Article();
        article.setTitle("Head Of U.S. Soccer Resigns After Equal Pay Backlash - HuffPost");
        article.setAuthor("Mark DeCambre");
        article.setPublishedAt(new Date(1584080218121L));
        articles.add(article);

        logger.info("added six articles");

        //news.setArticles(articles);
        articles.sort(Comparator.comparing(Article::getPublishedAt));
        logger.info("articles are sorted by date");

        List<Article> predictedArticleList = articles.subList(0,5);
        logger.info("getting article first 5 sorted by date articles");

        Mockito.when(newsHandler.getTopArticles(5)).thenReturn(articles.subList(0,5));
        assertEquals(predictedArticleList, newsHandler.getTopArticles(5));
    }

}