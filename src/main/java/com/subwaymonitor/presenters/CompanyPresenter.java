package com.subwaymonitor.presenters;

import com.subwaymonitor.models.Company;

public class CompanyPresenter {

    private String name;

    public CompanyPresenter(Company company) {
        if (company != null) {
            this.name = company.getName();
        }
    }

    public String getName() {
        return name;
    }

}
