package com.coding.willlegend.internhelper.bean;

import android.widget.ImageView;

/**
 * Created by user on 2016/2/22.
 */
public class Job_Information {
    private ImageView position_image;
    private String position_name;
    private String company_name;
    private String company_address;
    private String publish_date;
    private String salary;
    private String company_description;
    private String position_description;

    public ImageView getPosition_image() {
        return position_image;
    }

    public void setPosition_image(ImageView position_image) {
        this.position_image = position_image;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCompany_description() {
        return company_description;
    }

    public void setCompany_description(String company_description) {
        this.company_description = company_description;
    }

    public String getPosition_description() {
        return position_description;
    }

    public void setPosition_description(String position_description) {
        this.position_description = position_description;
    }
}
