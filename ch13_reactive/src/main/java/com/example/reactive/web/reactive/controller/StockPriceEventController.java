package com.example.reactive.web.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class StockPriceEventController {

    @GetMapping("/stocks/price/{stockCode}")
    Flux<String> retrieveStockPriceHardCoded(@PathVariable("stockCode") String stockCode) {
        return Flux.interval(Duration.ofSeconds(2)).map(l -> getCurrentDate() + ":" +
                getRandomNumber(100, 125)).log();
    }

    private int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private String getCurrentDate() {
        return (new Date()).toString();
    }
}
