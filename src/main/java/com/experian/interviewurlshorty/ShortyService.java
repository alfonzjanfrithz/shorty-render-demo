package com.experian.interviewurlshorty;

public interface ShortyService {
    String shorten(String url);

    String resolve(String url);
}
