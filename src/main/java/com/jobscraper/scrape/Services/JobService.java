package com.jobscraper.scrape.Services;

import com.jobscraper.scrape.Models.Job;
import com.jobscraper.scrape.Repository.IJobRepository;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService
{
    IJobRepository jobRepository;

    public JobService(IJobRepository jobRepository)
    {
        this.jobRepository = jobRepository;

        getJobTitles();
    }

    public void getJobTitles()
    {
        try
        {
            var doc = Jsoup.connect("https://thehub.io/jobs?countryCode=DK").get();
            var elements = doc.getAllElements().select("span.card-job-find-list__position").eachText();

            for(var element : elements)
            {
                jobRepository.save(new Job(element));
            }
        }
        catch (Exception e)
        {
            System.out.println("Error happened in JobService, getJobTitles(): " + e);
        }

    }

}
