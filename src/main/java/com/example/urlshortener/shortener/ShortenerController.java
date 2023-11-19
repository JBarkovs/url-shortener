package com.example.urlshortener.shortener;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortenerController {
    public final ShortenerService shortenerService;

    @GetMapping("/{code}")
    public void getUrl(@PathVariable String code, HttpServletResponse response) {
        shortenerService.getOriginalUrl(code, response);
    }

    @PostMapping
    public String shortenUrl(String url) {
        return shortenerService.getShortUrl(url);
    }
}
