package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import static java.util.Objects.requireNonNull;

public class ListSection extends Section{
    private List<String> items;

    public List<String> getItems() {
        return items;
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public  ListSection(List<String> items) {
        this.items = requireNonNull(items, "items must not be null");
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
