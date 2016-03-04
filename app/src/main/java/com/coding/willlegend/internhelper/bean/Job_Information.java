package com.coding.willlegend.internhelper.bean;

import android.widget.ImageView;
import java.io.Serializable;
/**
 * Created by user on 2016/2/22.
 */
public class Job_Information implements Serializable {
    private String position_image;
    private String position_name;
    private String company_name;
    private String company_address;
    private String publish_date;
    private int minsalary;
    private int maxsalary;
    private String uuid;
    private String salary;

    private String position_degree;
    private String position_month;
    private String position_day;
    private String attraction;
    private String address_detail;
    private String position_description;
    private String deadline;
    private String cuuid;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPosition_image() {
        return position_image;
    }

    public void setPosition_image(String position_image) {
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

    public int getMinsalary() {
        return minsalary;
    }

    public void setMinsalary(int minsalary) {
        this.minsalary = minsalary;
    }

    public int getMaxsalary() {
        return maxsalary;
    }

    public void setMaxsalary(int maxsalary) {
        this.maxsalary = maxsalary;
    }

    public String getPosition_degree() {
        return position_degree;
    }

    public void setPosition_degree(String position_degree) {
        this.position_degree = position_degree;
    }

    public String getPosition_month() {
        return position_month;
    }

    public void setPosition_month(String position_month) {
        this.position_month = position_month;
    }

    public String getPosition_day() {
        return position_day;
    }

    public void setPosition_day(String position_day) {
        this.position_day = position_day;
    }

    public String getAttraction() {
        return attraction;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getPosition_description() {
        return position_description;
    }

    public void setPosition_description(String position_description) {
        this.position_description = position_description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCuuid() {
        return cuuid;
    }

    public void setCuuid(String cuuid) {
        this.cuuid = cuuid;
    }
}
