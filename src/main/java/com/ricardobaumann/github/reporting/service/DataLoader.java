package com.ricardobaumann.github.reporting.service;

import com.ricardobaumann.github.reporting.repo.SiteMetricRepo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

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
        try (Reader in = new FileReader(String.format("%s/2018_01_report.csv", inputDataPath))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
            for (CSVRecord record : records) {
                System.out.println(record.get("site"));
                System.out.println(record.get("revenue"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
