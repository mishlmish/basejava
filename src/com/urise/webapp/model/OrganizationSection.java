package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import static java.util.Objects.requireNonNull;

public class OrganizationSection extends AbstractSection {
    private List<Organization> organizations;

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public OrganizationSection() {}

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = requireNonNull(organizations, "organizations can not be null") ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrganizationSection that = (OrganizationSection) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }
}
