package com.github.ricardobaumann.reporting.controller;

import com.github.ricardobaumann.reporting.model.Report;
import com.github.ricardobaumann.reporting.service.SiteMetricService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class MetricsControllerTest {

    @Mock
    private SiteMetricService siteMetricService;

    @InjectMocks
    private MetricsController metricsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(metricsController).build();
    }

    @Test
    public void shouldReturnReportDataForParams() throws Exception {
        //Given
        when(siteMetricService.getReport("site", 1))
                .thenReturn(
                        new Report(1L, 2L, 3L, 4L, new BigDecimal("5.55"), new BigDecimal("6.66"), new BigDecimal("7.77"), new BigDecimal("8.88"), new BigDecimal("9.99")));

        //When //Then
        mockMvc.perform(get("/reports?site={site}&month={month}", "site", 1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.requests", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.impressions", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clicks", is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions", is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.revenue", is(5.55)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ctr", is(6.66)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cr", is(7.77)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fillRate", is(8.88)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eCpm", is(9.99)));
    }

    @Test
    public void shouldReturnReportDataForEmptyParams() throws Exception {
        //Given
        when(siteMetricService.getReport(null, null))
                .thenReturn(
                        new Report(1L, 2L, 3L, 4L, new BigDecimal("5.55"), new BigDecimal("6.66"), new BigDecimal("7.77"), new BigDecimal("8.88"), new BigDecimal("9.99")));

        //When //Then
        mockMvc.perform(get("/reports"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.requests", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.impressions", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clicks", is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions", is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.revenue", is(5.55)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ctr", is(6.66)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cr", is(7.77)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fillRate", is(8.88)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eCpm", is(9.99)));
    }
}