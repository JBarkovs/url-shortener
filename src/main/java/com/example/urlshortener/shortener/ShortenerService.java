package com.example.urlshortener.shortener;

import com.example.urlshortener.encoder.Encoder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import static jakarta.servlet.http.HttpServletResponse.SC_MOVED_TEMPORARILY;
import static java.util.Optional.ofNullable;

@Service
public class ShortenerService {
    public static final String ALGORITHM = "SHA-256";
    public static final int CODE_LENGTH = 4;

    private final Encoder encoder;
    private final ShortenerDao shortenerDao;

    public void getOriginalUrl(String shortCode, HttpServletResponse response) {
        ofNullable(shortenerDao.getOriginalUrl(shortCode))
                .ifPresent(originalUrl -> {
                    response.setStatus(SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", originalUrl);
                });
    }

    public ShortenerService(ShortenerDao shortenerDao) {
        this.encoder = new Encoder(ALGORITHM);
        this.shortenerDao = shortenerDao;
    }

    public String getShortUrl(String originalURL) {
        String shortCode = getShortCode(originalURL);
        return "http://localhost:8080/%s".formatted(shortCode);
    }

    private String getShortCode(String originalURL) {
        return ofNullable(shortenerDao.getShortCode(originalURL))
                .orElseGet(() -> generateAndCacheShortCode(originalURL));
    }

    /**
     * Generates short code from originalURL by retrieving a substring from the encoded String.
     * Keeps regenerating short code, if generated code is already used.
     * @param originalURL
     * @return short code String
     */
    private String generateAndCacheShortCode(String originalURL) {
        String encoded = encoder.encodeString(originalURL);
        String shortCode = encoded.substring(0, CODE_LENGTH);
        return ofNullable(shortenerDao.getOriginalUrl(shortCode))
                .map(url -> generateAndCacheShortCode(shortCode))
                .orElseGet(() -> {
                    shortenerDao.addUrl(originalURL, shortCode);
                    return shortCode;
                });
    }
}
