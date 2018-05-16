package com.ricardobaumann.github.reporting.repo;

import com.ricardobaumann.github.reporting.model.SiteMetric;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteMetricRepo extends CrudRepository<SiteMetric,Long> {
}
