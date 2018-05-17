package com.github.ricardobaumann.reporting.service;

import com.github.ricardobaumann.reporting.model.SiteMetric;
import com.github.ricardobaumann.reporting.repo.SiteMetricRepo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SiteMetricServiceSuccessTest {

    private final SiteMetricRepo siteMetricRepo = mock(SiteMetricRepo.class);

    private final String inputPath = new File("src/test/resources/ok").getAbsolutePath();

    private final SiteMetricService siteMetricService = new SiteMetricService(siteMetricRepo, inputPath);
    private List<SiteMetric> metrics;
    private MathContext mathContext = new MathContext(3, RoundingMode.UP);

    @Before
    public void setup() {
        ArgumentCaptor<List<SiteMetric>> metricArgumentCaptor = ArgumentCaptor.forClass(List.class);

        //Given
        when(siteMetricRepo.saveAll(any())).thenReturn(null);

        //When
        siteMetricService.reloadDatabase();

        //Then
        verify(siteMetricRepo, times(2)).saveAll(metricArgumentCaptor.capture());
        metrics = metricArgumentCaptor.getAllValues().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    @Test
    public void shouldLoadFilesSucessfully() {
        assertThat(metrics)
                .containsExactlyInAnyOrder(
                        new SiteMetric("desktop web", 12483775, 11866157, 30965, 7608, new BigDecimal("23555.46"), 1),
                        new SiteMetric("desktop web", 11243875, 10366355, 40456, 1456, new BigDecimal("15745.32"), 2)
                );
    }

    @Test
    public void shouldCalculateCtr() {
        assertThat(metrics.stream()
                .map(SiteMetric::getCtr)
                .map(bigDecimal -> bigDecimal.round(mathContext))
                .map(BigDecimal::doubleValue).collect(Collectors.toList()))
                .containsExactlyInAnyOrder
                        (0.261, 0.391);
    }

    @Test
    public void shouldCalculateCr() {
        assertThat(metrics.stream()
                .map(SiteMetric::getCr)
                .map(bigDecimal -> bigDecimal.round(mathContext))
                .map(BigDecimal::doubleValue).collect(Collectors.toList()))
                .containsExactlyInAnyOrder
                        (0.0642, 0.0141);
    }

    @Test
    public void shouldCalculateFillRate() {
        assertThat(metrics.stream()
                .map(SiteMetric::getFillRate)
                .map(bigDecimal -> bigDecimal.round(mathContext))
                .map(BigDecimal::doubleValue).collect(Collectors.toList()))
                .containsExactlyInAnyOrder
                        (95.1, 92.2);
    }

    @Test
    public void shouldCalculateECpm() {
        assertThat(metrics.stream()
                .map(SiteMetric::geteCpm)
                .map(bigDecimal -> bigDecimal.round(mathContext))
                .map(BigDecimal::doubleValue).collect(Collectors.toList()))
                .containsExactlyInAnyOrder
                        (3.55E7, 2.62E7);
    }

}