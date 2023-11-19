package com.example.urlshortener.shortener;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortenerController {
    public final ShortenerService shortenerService;

    @PostMapping
    public String shortenUrl(String url) {
        return shortenerService.getShortUrl(url);
    }
}
