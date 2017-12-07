package com.jk.provide.entity;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable{
	private static final long serialVersionUID = -4668785275326623585L;


	private Integer userId;

    private String myid;

    private String account;

    private String name;

    private Integer organizeId;

    private String organizeName;

    private Integer dutyId;

    private Integer titleId;

    private String password;

    private String email;

    private String lang;

    private String theme;

    private Date firstVisit;

    private Date previousVisit;

    private Date lastVisits;

    private Integer loginCount;

    private Integer isemployee;

    private String status;

    private String ip;

    private String description;

    private Integer questionId;

    private String answer;

    private Integer isonline;

    private Date created;

    private Date lastmod;

    private Integer creater;

    private Integer modifyer;

    private String tel;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMyid() {
        return myid;
    }

    public void setMyid(String myid) {
        this.myid = myid == null ? null : myid.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(Integer organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName == null ? null : organizeName.trim();
    }

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public Date getFirstVisit() {
        return firstVisit;
    }

    public void setFirstVisit(Date firstVisit) {
        this.firstVisit = firstVisit;
    }

    public Date getPreviousVisit() {
        return previousVisit;
    }

    public void setPreviousVisit(Date previousVisit) {
        this.previousVisit = previousVisit;
    }

    public Date getLastVisits() {
        return lastVisits;
    }

    public void setLastVisits(Date lastVisits) {
        this.lastVisits = lastVisits;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getIsemployee() {
        return isemployee;
    }

    public void setIsemployee(Integer isemployee) {
        this.isemployee = isemployee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getIsonline() {
        return isonline;
    }

    public void setIsonline(Integer isonline) {
        this.isonline = isonline;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastmod() {
        return lastmod;
    }

    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getModifyer() {
        return modifyer;
    }

    public void setModifyer(Integer modifyer) {
        this.modifyer = modifyer;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }
}