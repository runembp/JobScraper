package com.jobscraper.scrape.Services;

import com.jobscraper.scrape.Models.Job;
import com.jobscraper.scrape.Repository.IJobRepository;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JobService
{
    IJobRepository jobRepository;

    public JobService(IJobRepository jobRepository)
    {
        this.jobRepository = jobRepository;
    }

    public void getJobTitles(String country)
    {
        try
        {
            if(country == null)
                country = "DK";
            var doc = Jsoup.connect("https://thehub.io/jobs?countryCode=" + country).get();
            var tests = doc.getAllElements().select("div[class=bullet-inline-list text-gray-600 fw500]");
            var elements = doc.getAllElements().select("span.card-job-find-list__position").eachText();

            int index = 0;

            for (var test : tests) {
                Elements test1 = test.getElementsByTag("span");

                var jobtitle = elements.get(index);
                var company = test1.get(0).text();
                var location = test1.get(1).text();
                var time = test1.get(2).text();

                var job = new Job(jobtitle, company, location, time);

                jobRepository.save(job);
                index++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error happened in JobService, getJobTitles(): " + e);
        }
    }
}
