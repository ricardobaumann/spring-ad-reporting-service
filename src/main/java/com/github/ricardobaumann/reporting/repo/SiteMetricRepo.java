package com.github.ricardobaumann.reporting.repo;

import com.github.ricardobaumann.reporting.model.Report;
import com.github.ricardobaumann.reporting.model.SiteMetric;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SiteMetricRepo extends CrudRepository<SiteMetric, Long> {

    @Query("select new com.github.ricardobaumann.reporting.model.Report(" +
            "sum(m.requests)," +
            "sum(m.impressions)," +
            "sum(m.clicks)," +
            "sum(m.conversions)," +
            "sum(m.revenue)," +
            "sum(m.ctr)," +
            "sum(m.cr)," +
            "sum(m.fillRate)," +
            "sum(m.eCpm))" +
            " from SiteMetric m where (m.site = ?1 or ?1 = '') and (m.month = ?2 or ?2 = 0)")
    Report findBySiteAndMonth(String site, Integer month);
}
