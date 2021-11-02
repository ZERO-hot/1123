package com.example.data;

public class MyBean {
    String ip;
    String des;

    public MyBean(String ip, String des) {
        this.ip = ip;
        this.des = des;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
