package com.henry.configuration;

import com.henry.record.BrandRecord;
import com.henry.record.CompanyRecord;
import com.henry.record.UserRecord;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("datasource")
public class DataSourceProperties {
   private UserRecord user;
   private CompanyRecord company;
   private BrandRecord brand;

    public UserRecord getUser() {
        return user;
    }

    public void setUser(UserRecord user) {
        this.user = user;
    }

    public CompanyRecord getCompany() {
        return company;
    }

    public void setCompany(CompanyRecord company) {
        this.company = company;
    }

    public BrandRecord getBrand() {
        return brand;
    }

    public void setBrand(BrandRecord brand) {
        this.brand = brand;
    }
}
