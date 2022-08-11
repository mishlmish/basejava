package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import static java.util.Objects.requireNonNull;

public class ListSection extends AbstractSection {
    private List<String> items;

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ListSection that = (ListSection) o;

        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return items != null ? items.hashCode() : 0;
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> items) {
        this.items = requireNonNull(items, "items must not be null");
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
