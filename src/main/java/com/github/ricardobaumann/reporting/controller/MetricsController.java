package com.github.ricardobaumann.reporting.controller;

import com.github.ricardobaumann.reporting.model.Report;
import com.github.ricardobaumann.reporting.service.SiteMetricService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    private SiteMetricService siteMetricService;

    MetricsController(SiteMetricService siteMetricService) {
        this.siteMetricService = siteMetricService;
    }

    @GetMapping("reports")
    public Report get(@RequestParam(required = false) String site,
                      @RequestParam(required = false) Integer month) {
        return siteMetricService.getReport(site, month);
    }

}
