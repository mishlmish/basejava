package com.urise.webapp.model;


import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public class Organization {
    private String name;
    private String webSite;
    private List<Position> positions;
    private Link link;

    public String webSite() {
        return webSite;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Organization(String name, String webSite, Position... positions) {
        this(name, webSite, asList(positions));
    }

    public Organization(String name, String webSite, List<Position> positions) {
        this.name = name;
        this.webSite = webSite;
        this.positions = positions;
        link = new Link(name, webSite);
    }

    public Organization(String name, String webSite, LocalDate startDate, LocalDate endDate, String title,
                        String description) {
        this(name, webSite, new Position(startDate, endDate, title, description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Organization that = (Organization) o;

        if (!positions.equals(that.positions)) {
            return false;
        }
        return link.equals(that.link);
    }

    @Override
    public int hashCode() {
        int result = positions.hashCode();
        result = 31 * result + link.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organisation(" + name + " " + webSite + " , " + positions + ")";
    }

    public static class Position {
        private LocalDate startDate;
        private LocalDate endDate;
        private String title;
        private String description;

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }



        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            this.startDate = requireNonNull(startDate, "startDate can not be null");
            this.endDate = requireNonNull(endDate, "endDate can not be null");
            this.title = requireNonNull(title, "title can not be null");
            this.description = description == null ? "" : description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Position position = (Position) o;

            if (!startDate.equals(position.startDate)) {
                return false;
            }
            if (!endDate.equals(position.endDate)) {
                return false;
            }
            if (!title.equals(position.title)) {
                return false;
            }
            return description.equals(position.description);
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + description.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Positions(" + startDate.getMonth().getValue() + "/" +
                    startDate.getYear() + " - " + endDate.getMonth().getValue() + "/" +
                    endDate.getYear() + " , " + title + " , " + description + ")";
        }

    }
}
