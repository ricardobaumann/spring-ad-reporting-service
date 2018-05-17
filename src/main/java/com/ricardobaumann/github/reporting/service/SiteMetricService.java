package com.ricardobaumann.github.reporting.service;

import com.ricardobaumann.github.reporting.model.Report;
import com.ricardobaumann.github.reporting.model.SiteMetric;
import com.ricardobaumann.github.reporting.repo.SiteMetricRepo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SiteMetricService {

    private SiteMetricRepo siteMetricRepo;
    private String inputDataPath;

    private static final String EMPTY = "";

    SiteMetricService(SiteMetricRepo siteMetricRepo, @Value("${input.data.path:}") String inputDataPath) {
        this.siteMetricRepo = siteMetricRepo;
        this.inputDataPath = inputDataPath;
    }

    public Report getReport(String site, Integer month) {
        return siteMetricRepo.findBySiteAndMonth(Optional.ofNullable(site).orElse(EMPTY),
                Optional.ofNullable(month).orElse(0));
    }

    @Scheduled(fixedRate = Integer.MAX_VALUE)
    void reloadDatabase() {
        if (StringUtils.isEmpty(inputDataPath)) {
            return;
        }
        siteMetricRepo.deleteAll();
        Arrays.stream(Objects.requireNonNull(
                new File(inputDataPath).listFiles()))
                .forEach(file -> reloadFromFile(String.format(file.getAbsolutePath(), inputDataPath)));
    }

    private void reloadFromFile(String file) {
        try (Reader in = new FileReader(file)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
            String[] fileParts = file.split("/");
            Integer month = Integer.parseInt(fileParts[fileParts.length - 1].split("_")[1]);
            siteMetricRepo.saveAll(StreamSupport.stream(records.spliterator(), false)
                    .map(record -> {
                        SiteMetric siteMetric = new SiteMetric();
                        siteMetric.setMonth(month);
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
