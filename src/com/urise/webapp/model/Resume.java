package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

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
        this.uuid = requireNonNull(uuid, "uuid can not be null");
        this.fullName = requireNonNull(fullName, "fullName can not be null");
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public void setContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public void setSection(SectionType type, AbstractSection value) {
        sections.put(type, value);
    }

    @Override
    public String toString() {
        return "uuid " + uuid + ", fullName " + fullName;
    }

    @Override
    public int compareTo(Resume r) {
        int cmp = fullName.compareTo(r.fullName);
        return cmp == 0 ? cmp : uuid.compareTo(r.uuid);
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

        if (!uuid.equals(resume.uuid)) {
            return false;
        }
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }
}
