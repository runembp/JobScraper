package com.jobscraper.scrape.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Job
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private String company;
    private String location;
    private String time;

    public Job(String jobTitle, String company, String location, String time) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.time = time;
    }
}
