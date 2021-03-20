package com.ablog.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOG")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOG_ID")
    private int id;

    @Column(name = "LOG_TIME")
    private Date time;

    @Column(name = "LOG_ACTION")
    private String action;

    @Column(name = "LOG_IP")
    private String ip;

    @Column(name = "LOG_MODULE")
    private String module;

    public Log(){}

    public Log(Date time, String action, String ip, String module) {
        this.time = time;
        this.action = action;
        this.ip = ip;
        this.module = module;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

}
