package com.example.urlshortener.shortener;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShortenerDao {
    //TODO add scheduler to clear old/unused entries
    private final Map<String, String> urlToShortCode;
    private final Map<String, String> shortCodeToUrl;

    //TODO add database calls instead of using local Maps
    public ShortenerDao() {
        urlToShortCode = new HashMap<>();
        shortCodeToUrl = new HashMap<>();
    }

    public String getShortCode(String originalURL) {
        return urlToShortCode.get(originalURL);
    }

    public String getOriginalUrl(String url) {
        return shortCodeToUrl.get(url);
    }

    public void addUrl(String originalUrl, String shortCode) {
        urlToShortCode.put(originalUrl, shortCode);
        shortCodeToUrl.put(shortCode, originalUrl);
    }
}
