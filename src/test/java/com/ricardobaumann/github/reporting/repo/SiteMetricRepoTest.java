package com.ricardobaumann.github.reporting.repo;

import com.ricardobaumann.github.reporting.model.Report;
import com.ricardobaumann.github.reporting.model.SiteMetric;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SiteMetricRepoTest {

    @Autowired
    private SiteMetricRepo siteMetricRepo;

    @Before
    public void setUp() {
        siteMetricRepo.deleteAll();
        SiteMetric metric1 = new SiteMetric("site1", 10, 20, 30, 40, new BigDecimal(50.1), 2);
        metric1.setCtr(new BigDecimal(10));
        metric1.seteCpm(new BigDecimal(20));
        metric1.setFillRate(new BigDecimal(30));
        metric1.seteCpm(new BigDecimal(40));
        metric1.setCr(new BigDecimal(45));


        SiteMetric metric2 = new SiteMetric("site2", 20, 30, 40, 50, new BigDecimal(60.1), 2);
        metric2.setCtr(new BigDecimal(15));
        metric2.seteCpm(new BigDecimal(25));
        metric2.setFillRate(new BigDecimal(35));
        metric2.seteCpm(new BigDecimal(45));
        metric2.setCr(new BigDecimal(55));

        SiteMetric metric3 = new SiteMetric("site2", 20, 30, 40, 50, new BigDecimal(60.1), 1);
        metric3.setCtr(new BigDecimal(15));
        metric3.seteCpm(new BigDecimal(25));
        metric3.setFillRate(new BigDecimal(35));
        metric3.seteCpm(new BigDecimal(45));
        metric3.setCr(new BigDecimal(55));

        siteMetricRepo.saveAll(Arrays.asList(
                metric1,
                metric2,
                metric3
        ));
    }

    @Test
    public void shouldFindAllSitesWhenSiteIsEmpty() {
        Report reports = siteMetricRepo.findBySiteAndMonth("", 2);

        assertThat(reports)
                .isEqualTo(
                        new Report(30L, 50L, 70L, 90L, new BigDecimal("110.20"), new BigDecimal("25.00"), new BigDecimal("100.00"), new BigDecimal("65.00"), new BigDecimal("85.00"))
                );
    }

    @Test
    public void shouldFindAllMonthsWhenMonthIsZero() {
        Report reports = siteMetricRepo.findBySiteAndMonth("site2", 0);

        assertThat(reports)
                .isEqualTo(
                        new Report(40L, 60L, 80L, 100L, new BigDecimal("120.20"), new BigDecimal("30.00"), new BigDecimal("110.00"), new BigDecimal("70.00"), new BigDecimal("90.00"))
                );

    }

    @Test
    public void shouldFindMonthAndMonthWhenSet() {
        Report reports = siteMetricRepo.findBySiteAndMonth("site2", 1);
        assertThat(reports)
                .isEqualTo(
                        new Report(20L, 30L, 40L, 50L, new BigDecimal("60.10"), new BigDecimal("15.00"), new BigDecimal("55.00"), new BigDecimal("35.00"), new BigDecimal("45.00"))
                );
    }

    @Test
    public void shouldFindEverything() {
        Report reports = siteMetricRepo.findBySiteAndMonth("", 0);

        assertThat(reports)
                .isEqualTo(
                        new Report(50L, 80L, 110L, 140L, new BigDecimal("170.30"), new BigDecimal("40.00"), new BigDecimal("155.00"), new BigDecimal("100.00"), new BigDecimal("130.00"))
                );
    }

}