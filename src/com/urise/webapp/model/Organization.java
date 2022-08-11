package com.urise.webapp.model;


import com.urise.webapp.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.urise.webapp.util.DateUtil.NOW;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public class Organization {
    private final String name;
    private final String url;
    private final List<Position> positions;
    private final Link link;

    public String url() {
        return url;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Organization(Link link, List<Position> positions) {
        this(link.getName(), link.getUrl(), positions);
    }

    public Organization(String name, String url, Position... positions) {
        this(name, url, asList(positions));
    }

    public Organization(String name, String url, List<Position> positions) {
        this.name = name;
        this.url = url;
        this.positions = positions;
        link = new Link(name, url);
    }

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title,
                        String description) {
        this(name, url, new Position(startDate, endDate, title, description));
    }

    public Organization(String name, String url, LocalDate startDate, String title,
                        String description) {
        this(name, url, startDate, NOW, title, description);
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
        return "Organisation(" + name + " " + url + " , " + positions + ")";
    }

    public static class Position {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

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

        public Position(int startYear, Month startMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title,
                        String description) {
            this(DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), title, description);
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
            return endDate != NOW ? "Positions(" + startDate.getMonth().getValue() + "/" +
                    startDate.getYear() + " - " + endDate.getMonth().getValue() + "/" +
                    endDate.getYear() + " , " + title + " , " + description + ")" :
                    "Positions(" + startDate.getMonth().getValue() + "/" +
                            startDate.getYear() + " - " + "Cейчас" + " , " + title + " , " + description + ")";
        }
    }
}
