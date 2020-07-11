package com.huse.pojo;

import java.util.List;

public class Telephonebook {
    private Integer id;

    private String name;

    private String department;

    private String duty;

    private String officeadress;

    private List<String> telephonenum;

    public List<String> getTelephonenum() {
        return telephonenum;
    }

    public void setTelephonenum(List<String> telephonenum) {
        this.telephonenum = telephonenum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getOfficeadress() {
        return officeadress;
    }

    public void setOfficeadress(String officeadress) {
        this.officeadress = officeadress == null ? null : officeadress.trim();
    }

    @Override
    public String toString() {
        return "Telephonebook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", duty='" + duty + '\'' +
                ", officeadress='" + officeadress + '\'' +
                ", telephonenum=" + telephonenum +
                '}';
    }
}