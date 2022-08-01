package com.urise.webapp.model;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

public class Organization {
    private Link homePage;

    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String description;

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title,
                        String description) {
        this.homePage = new Link(name, url);
        this.startDate = requireNonNull(startDate);
        this.endDate = requireNonNull(endDate);
        this.title = requireNonNull(title);
        this.description = description;
    }


}
