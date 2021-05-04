package com.jobscraper.scrape.Services;

import com.jobscraper.scrape.Models.Job;
import com.jobscraper.scrape.Repository.IJobRepository;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class JobService
{
    IJobRepository jobRepository;

    public JobService(IJobRepository jobRepository)
    {
        this.jobRepository = jobRepository;

        getJobTitles();
    }

    public List<String> getjobtitle() throws IOException {
        var doc = Jsoup.connect("https://thehub.io/jobs?countryCode=DK").get();
        var elements = doc.getAllElements().select("span.card-job-find-list__position").eachText();


        return elements;
    }

    public void getJobTitles()
    {
        try
        {

            var doc = Jsoup.connect("https://thehub.io/jobs?countryCode=DK").get();
            var tests = doc.getAllElements().select("div[class=bullet-inline-list text-gray-600 fw500]");

            int index = 0;

            for (var test : tests) {
                Elements test1 = test.getElementsByTag("span");

                var jobtitle = getjobtitle().get(index);
                var company = test1.get(0).text();
                var location = test1.get(1).text();
                var time = test1.get(2).text();

                jobRepository.save(new Job(jobtitle, company, location, time));

                index++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error happened in JobService, getJobTitles(): " + e);
        }

    }

}
