package com.experian.interviewurlshorty;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class ShortyController {
    private ShortyService shortyService;

    @PostMapping
    public ShortyResponse shorten(@RequestBody ShortyRequest req) {
        String shorten = shortyService.shorten(req.url);
        return new ShortyResponse(shorten);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<HttpStatus> resolve(@PathVariable String hash) {
        String target = shortyService.resolve(hash);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(target))
                .header(HttpHeaders.CONNECTION, "close")
                .build();
    }
}
