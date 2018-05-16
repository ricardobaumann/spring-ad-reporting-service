package com.ricardobaumann.github.reporting.service;

import com.ricardobaumann.github.reporting.repo.SiteMetricRepo;

import org.junit.Test;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataLoaderValidationTest {

    private final SiteMetricRepo siteMetricRepo = mock(SiteMetricRepo.class);

    private final String inputPath = new File("src/test/resources/fail").getAbsolutePath();

    private final DataLoader dataLoader = new DataLoader(siteMetricRepo, inputPath);

    @Test
    public void shouldNotSave() {
        //Given
        when(siteMetricRepo.saveAll(any())).thenReturn(null);

        //When
        dataLoader.reloadDatabase();

        //Then
        verify(siteMetricRepo, never()).saveAll(any());
    }
}