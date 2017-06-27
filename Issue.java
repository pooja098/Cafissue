package com.example.pooja.cafissues;

/**
 * Created by pooja on 10/9/16.
 */
public class Issue {
    String subject;
    String desc;
    long issueno;
    String usrid;
    boolean status,verify;
    static int i = 0;

    public Issue(){

    }

    public Issue(String subject, String desc, long issueno, String usrid) {
        this.subject = subject;
        this.desc = desc;
        this.issueno = issueno;
        this.usrid = usrid;
        this.status = false;
        this.verify = false;
    }

    public Issue(String subject,String desc,String usrid,boolean status){
        this.subject = subject;
        this.desc = desc;
        this.usrid = usrid;
        this.status = status;
        this.issueno = ++i;
        this.verify = false;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getIssueno() {
        return issueno;
    }

    public void setIssueno(long issueno) {
        this.issueno = issueno;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }
}
