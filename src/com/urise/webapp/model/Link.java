package com.urise.webapp.model;

import static java.util.Objects.requireNonNull;

public class Link {
    private   String name;

    private   String url;

    public Link(String name, String url) {
        this.name = requireNonNull(name);
        this.url = url;
    }
}
