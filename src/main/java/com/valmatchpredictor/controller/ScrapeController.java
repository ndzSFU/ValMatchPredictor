package com.valmatchpredictor.controller;

import com.valmatchpredictor.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class ScrapeController {

    @Autowired
    private DataService dataService;

    @PostMapping("/scrape")
    public ResponseEntity<Void> triggerScrape() {
        dataService.updateAllMatches();
        return ResponseEntity.accepted().build();
    }
}
