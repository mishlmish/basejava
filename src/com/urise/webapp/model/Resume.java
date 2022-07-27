package com.urise.webapp.model;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = requireNonNull(uuid, "uuid must be not null");
        this.fullName = requireNonNull(fullName, "fullName must be not null");
    }

    @Override
    public String toString() {
        return "uuid " + uuid + ", fullName " + fullName;
    }

    @Override
    public int compareTo(Resume r) {
        return uuid.compareTo(r.uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
