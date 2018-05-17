package com.ricardobaumann.github.reporting.service;

import com.ricardobaumann.github.reporting.repo.SiteMetricRepo;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SiteMetricServiceGetReportTest {

    private SiteMetricRepo siteMetricRepo = Mockito.mock(SiteMetricRepo.class);

    private SiteMetricService siteMetricService = new SiteMetricService(siteMetricRepo, null);

    @Test
    public void mapFilledSiteAndMonthParams() {
        //Given
        when(siteMetricRepo.findBySiteAndMonth("site", 1)).thenReturn(null);

        //When
        siteMetricService.getReport("site", 1);

        //Then
        verify(siteMetricRepo).findBySiteAndMonth("site", 1);
    }

    @Test
    public void shouldMapEmptyParams() {
        //Given
        when(siteMetricRepo.findBySiteAndMonth("", 0)).thenReturn(null);

        //When
        siteMetricService.getReport(null, null);

        //Then
        verify(siteMetricRepo).findBySiteAndMonth("", 0);
    }

}