package com.study.bean;

public class Department {
    private String depno;
    private String depname;
    private String location;

    public Department(String depno, String depname, String location) {
        this.depno = depno;
        this.depname = depname;
        this.location = location;
    }

    public String getDepno() {
        return depno;
    }

    public String getDepname() {
        return depname;
    }

    public String getLocation() {
        return location;
    }

    public void setDepno(String depno) {
        this.depno = depno;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depno='" + depno + '\'' +
                ", depname='" + depname + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
