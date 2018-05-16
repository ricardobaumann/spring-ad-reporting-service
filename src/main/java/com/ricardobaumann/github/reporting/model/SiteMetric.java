package com.ricardobaumann.github.reporting.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SiteMetric {

    @Id
    @GeneratedValue
    private Long id;

    private String site;

    private Integer requests;

    private Integer impressions;

    private Integer clicks;

    private Integer conversions;

    private BigDecimal revenue;

    private BigDecimal ctr;

    private BigDecimal cr;

    private BigDecimal fillRate;

    private BigDecimal eCpm;

    public SiteMetric(String site, Integer requests, Integer impressions, Integer clicks, Integer conversions, BigDecimal revenue) {
        this.site = site;
        this.requests = requests;
        this.impressions = impressions;
        this.clicks = clicks;
        this.conversions = conversions;
        this.revenue = revenue;
    }

    public SiteMetric() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public Integer getImpressions() {
        return impressions;
    }

    public void setImpressions(Integer impressions) {
        this.impressions = impressions;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getConversions() {
        return conversions;
    }

    public void setConversions(Integer conversions) {
        this.conversions = conversions;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getCtr() {
        return ctr;
    }

    public void setCtr(BigDecimal ctr) {
        this.ctr = ctr;
    }

    public BigDecimal getCr() {
        return cr;
    }

    public void setCr(BigDecimal cr) {
        this.cr = cr;
    }

    public BigDecimal getFillRate() {
        return fillRate;
    }

    public void setFillRate(BigDecimal fillRate) {
        this.fillRate = fillRate;
    }

    public BigDecimal geteCpm() {
        return eCpm;
    }

    public void seteCpm(BigDecimal eCpm) {
        this.eCpm = eCpm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SiteMetric that = (SiteMetric) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(site, that.site) &&
                Objects.equals(requests, that.requests) &&
                Objects.equals(impressions, that.impressions) &&
                Objects.equals(clicks, that.clicks) &&
                Objects.equals(conversions, that.conversions) &&
                Objects.equals(revenue, that.revenue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, site, requests, impressions, clicks, conversions, revenue);
    }

    @Override
    public String toString() {
        return "SiteMetric{" +
                "id=" + id +
                ", site='" + site + '\'' +
                ", requests=" + requests +
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