package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import static java.util.Objects.requireNonNull;

public class OrganizationSection extends Section{
    private List<Organization> organizations;

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public OrganizationSection(){}

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = requireNonNull(organizations, "organizations must not be null") ;
    }
}
