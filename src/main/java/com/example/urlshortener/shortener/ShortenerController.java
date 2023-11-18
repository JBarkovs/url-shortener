package com.example.urlshortener.shortener;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("short")
public class ShortenerController {
    public final ShortenerService shortenerService;

    @GetMapping
    public String urlToShort() {
        return shortenerService.urlToShort("Hello, short");
    }
}
