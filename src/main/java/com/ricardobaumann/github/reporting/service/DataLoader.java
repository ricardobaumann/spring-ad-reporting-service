package com.ricardobaumann.github.reporting.service;

import com.ricardobaumann.github.reporting.model.SiteMetric;
import com.ricardobaumann.github.reporting.repo.SiteMetricRepo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DataLoader {

    private SiteMetricRepo siteMetricRepo;
    private String inputDataPath;

    DataLoader(SiteMetricRepo siteMetricRepo, @Value("${input.data.path}") String inputDataPath) {
        this.siteMetricRepo = siteMetricRepo;
        this.inputDataPath = inputDataPath;
    }

    @Scheduled(fixedRate = Integer.MAX_VALUE)
    void reloadDatabase() {
        reloadFromFile(String.format("%s/2018_01_report.csv", inputDataPath));
        reloadFromFile(String.format("%s/2018_02_report.csv", inputDataPath));
    }

    private void reloadFromFile(String file) {
        try (Reader in = new FileReader(file)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);

            siteMetricRepo.saveAll(StreamSupport.stream(records.spliterator(), false)
                    .map(record -> {
                        SiteMetric siteMetric = new SiteMetric();
                        siteMetric.setClicks(Integer.parseInt(record.get("clicks").trim()));
                        siteMetric.setConversions(Integer.parseInt(record.get("conversions").trim()));
                        siteMetric.setImpressions(Integer.parseInt(record.get("impressions").trim()));
                        siteMetric.setRequests(Integer.parseInt(record.get("requests").trim()));
                        siteMetric.setRevenue(new BigDecimal(record.get("revenue").trim()));
                        siteMetric.setSite(record.get("site").trim());
                        siteMetric.setCtr(new BigDecimal(((double) siteMetric.getClicks() / siteMetric.getImpressions()) * 100));
                        siteMetric.setCr(new BigDecimal(((double) siteMetric.getConversions() / siteMetric.getImpressions()) * 100));
                        siteMetric.setFillRate(new BigDecimal(((double) siteMetric.getImpressions() / siteMetric.getRequests()) * 100));
                        siteMetric.seteCpm(new BigDecimal(((double) siteMetric.getRevenue().intValue() * 1000) + siteMetric.getImpressions()));
                        return siteMetric;
                    }).collect(Collectors.toList()));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }


}
