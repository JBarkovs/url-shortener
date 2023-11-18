package com.example.urlshortener.shortener;

import org.springframework.stereotype.Service;

@Service
public class ShortenerService {

    public String urlToShort(String url) {
        return url;
    }
}
