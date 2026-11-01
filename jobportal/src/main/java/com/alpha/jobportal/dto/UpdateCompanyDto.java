package com.alpha.jobportal.dto;

public class UpdateCompanyDto {

    private String name;
    private long phone;
    private String address;
    private String type;
    private int noofemps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNoofemps() {
        return noofemps;
    }

    public void setNoofemps(int noofemps) {
        this.noofemps = noofemps;
    }
}