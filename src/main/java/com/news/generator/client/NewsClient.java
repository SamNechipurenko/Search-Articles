package com.news.generator.client;

import com.news.generator.model.News;

public interface NewsClient {
    News getAllCountryNews(String country);
}
