package com.jk.entity;

import java.io.Serializable;


public class LoginUser implements Serializable{
	
	private static final long serialVersionUID = 6052978279843083799L;

	private String cId;

    private String cLoginid;

    private String cName;

    private String cPassword;

    private String cMail;

    private String cIp;

    private String cXmjp;

    private String cCorp;

    private String cDept;

    private Byte nValid;

    private Short nOrder;

    private String usercode;

    private String userpost;

    private String cZw;

    private String cLinkuser;

    private Integer nRylx;

    private Byte nShowUser;

    private Integer nTxl;

    private Byte nSfkq;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId == null ? null : cId.trim();
    }

    public String getcLoginid() {
        return cLoginid;
    }

    public void setcLoginid(String cLoginid) {
        this.cLoginid = cLoginid == null ? null : cLoginid.trim();
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword == null ? null : cPassword.trim();
    }

    public String getcMail() {
        return cMail;
    }

    public void setcMail(String cMail) {
        this.cMail = cMail == null ? null : cMail.trim();
    }

    public String getcIp() {
        return cIp;
    }

    public void setcIp(String cIp) {
        this.cIp = cIp == null ? null : cIp.trim();
    }

    public String getcXmjp() {
        return cXmjp;
    }

    public void setcXmjp(String cXmjp) {
        this.cXmjp = cXmjp == null ? null : cXmjp.trim();
    }

    public String getcCorp() {
        return cCorp;
    }

    public void setcCorp(String cCorp) {
        this.cCorp = cCorp == null ? null : cCorp.trim();
    }

    public String getcDept() {
        return cDept;
    }

    public void setcDept(String cDept) {
        this.cDept = cDept == null ? null : cDept.trim();
    }

    public Byte getnValid() {
        return nValid;
    }

    public void setnValid(Byte nValid) {
        this.nValid = nValid;
    }

    public Short getnOrder() {
        return nOrder;
    }

    public void setnOrder(Short nOrder) {
        this.nOrder = nOrder;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUserpost() {
        return userpost;
    }

    public void setUserpost(String userpost) {
        this.userpost = userpost == null ? null : userpost.trim();
    }

    public String getcZw() {
        return cZw;
    }

    public void setcZw(String cZw) {
        this.cZw = cZw == null ? null : cZw.trim();
    }

    public String getcLinkuser() {
        return cLinkuser;
    }

    public void setcLinkuser(String cLinkuser) {
        this.cLinkuser = cLinkuser == null ? null : cLinkuser.trim();
    }

    public Integer getnRylx() {
        return nRylx;
    }

    public void setnRylx(Integer nRylx) {
        this.nRylx = nRylx;
    }

    public Byte getnShowUser() {
        return nShowUser;
    }

    public void setnShowUser(Byte nShowUser) {
        this.nShowUser = nShowUser;
    }

    public Integer getnTxl() {
        return nTxl;
    }

    public void setnTxl(Integer nTxl) {
        this.nTxl = nTxl;
    }

    public Byte getnSfkq() {
        return nSfkq;
    }

    public void setnSfkq(Byte nSfkq) {
        this.nSfkq = nSfkq;
    }
}