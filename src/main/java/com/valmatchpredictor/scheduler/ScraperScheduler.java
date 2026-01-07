package com.valmatchpredictor.scheduler;

import com.valmatchpredictor.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class ScraperScheduler {

    @Autowired
    private DataService dataService;

    //Run every day at 2am
    @Scheduled(cron = "0 0 17 * * *")
    public void scrapeAllTeams(){
        System.out.println("Scraper started at " + LocalDateTime.now());
        dataService.updateAllMatches();
        System.out.println("Scraper finished at " + LocalDateTime.now());
    }
}
