package com.example.urlshortener.encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encoder {
    private final MessageDigest digest;

    public Encoder(String algorithm) {
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to get %s MessageDigest instance".formatted(algorithm),e);
        }
    }

    public String encodeString(String source) {
        byte[] hashBytes = digest.digest(source.getBytes());
        return Base64.getUrlEncoder().encodeToString(hashBytes);
    }
}
