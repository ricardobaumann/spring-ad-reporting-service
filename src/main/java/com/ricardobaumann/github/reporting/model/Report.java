package com.ricardobaumann.github.reporting.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Report {

    private final Long requests;

    private final Long impressions;

    private final Long clicks;

    private final Long conversions;

    private final BigDecimal revenue;

    private final BigDecimal ctr;

    private final BigDecimal cr;

    private final BigDecimal fillRate;

    private final BigDecimal eCpm;

    public Report(Long requests, Long impressions, Long clicks, Long conversions, BigDecimal revenue, BigDecimal ctr, BigDecimal cr, BigDecimal fillRate, BigDecimal eCpm) {
        this.requests = requests;
        this.impressions = impressions;
        this.clicks = clicks;
        this.conversions = conversions;
        this.revenue = revenue;
        this.ctr = ctr;
        this.cr = cr;
        this.fillRate = fillRate;
        this.eCpm = eCpm;
    }

    public Long getRequests() {
        return requests;
    }

    public Long getImpressions() {
        return impressions;
    }

    public Long getClicks() {
        return clicks;
    }

    public Long getConversions() {
        return conversions;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public BigDecimal getCtr() {
        return ctr;
    }

    public BigDecimal getCr() {
        return cr;
    }

    public BigDecimal getFillRate() {
        return fillRate;
    }

    public BigDecimal geteCpm() {
        return eCpm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        return Objects.equals(requests, report.requests) &&
                Objects.equals(impressions, report.impressions) &&
                Objects.equals(clicks, report.clicks) &&
                Objects.equals(conversions, report.conversions) &&
                Objects.equals(revenue, report.revenue) &&
                Objects.equals(ctr, report.ctr) &&
                Objects.equals(cr, report.cr) &&
                Objects.equals(fillRate, report.fillRate) &&
                Objects.equals(eCpm, report.eCpm);
    }

    @Override
    public int hashCode() {

        return Objects.hash(requests, impressions, clicks, conversions, revenue, ctr, cr, fillRate, eCpm);
    }

    @Override
    public String toString() {
        return "Report{" +
                "requests=" + requests +
                ", impressions=" + impressions +
                ", clicks=" + clicks +
                ", conversions=" + conversions +
                ", revenue=" + revenue +
                ", ctr=" + ctr +
                ", cr=" + cr +
                ", fillRate=" + fillRate +
                ", eCpm=" + eCpm +
                '}';
    }
}
