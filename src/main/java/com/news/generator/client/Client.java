package com.news.generator.client;

import com.news.generator.model.News;

public interface Client {
    News getAllCountryNews(String country);
}
