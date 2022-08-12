package com.urise.webapp.model;

import static java.util.Objects.requireNonNull;

public enum ContactType {
    PHONE("Тел."),
    MOBILE("Мобильный"),
    HOME_PHONE("Домашний тел."),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    public String getTitle() {
        return title;
    }

    ContactType(String title) {
        this.title = title;
    }

    public static class Link {
        private final String name;

        private final String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public Link(String name, String url) {
            this.name = requireNonNull(name, "name can not be null");
            this.url = url;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Link link = (Link) o;

            if (!name.equals(link.name)) {
                return false;
            }
            return url != null ? url.equals(link.url) : link.url == null;
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + (url != null ? url.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Name (" + name + " , " + " url " + url;
        }
    }
}
