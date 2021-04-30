package com.jobscraper.scrape.Repository;

import com.jobscraper.scrape.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobRepository extends JpaRepository<Job, Long>
{
}
